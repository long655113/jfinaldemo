/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.service;

import com.znima.AddNovelThread;
import com.znima.ModelUtil;
import com.znima.config.MyConstants;
import com.znima.dto.GetNovelConfigDto;
import com.znima.dto.NovelDto;
import com.znima.dto.NovelItemDto;
import com.znima.entity.GetNovelConfig;
import com.znima.entity.Novel;
import com.znima.entity.NovelItem;
import com.znima.job.QuartzJob;
import com.znima.result.Msg;
import com.znima.util.FileUtil;
import com.znima.util.JsoupUtil;
import com.znima.util.MyStringUtil;
import com.znima.util.PdfUtil;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class NovelService {

    private static Logger logger = LoggerFactory.getLogger(NovelService.class);

    private final static String logPre = "[" + NovelService.class.getSimpleName() + "]";

    public List<Novel> getNovels() {
        List<Novel> novels = Novel.dao.getNovels();
        return novels;
    }

    public Msg addNovel(GetNovelConfigDto configDto) {

        Msg msg = new Msg();

        NovelDto novelDto = JsoupUtil.getNovel(configDto);

        logger.info(logPre + "addNovel: novel:" + novelDto);

        if (novelDto == null) {
            msg.setCode("110");
            msg.setMsg("解释目录url失败");
            return msg;
        }

        Novel novel = new Novel();
        novel.put("url", novelDto.getIndexUrl());
        novel.put("novelName", novelDto.getNovelName());
        novel.put("desc", novelDto.getDesc());
        novel.put("author", novelDto.getAuthor());
        novel.put("image", novelDto.getImage());
        novel.put("createTime", new Date());

        boolean save = novel.save();

        Integer id = novel.get("id");

        logger.info(this.logPre + "addNovel, 章节数：" + novelDto.getItems().size());

        if (novelDto.getItems().isEmpty()) {
            msg.setCode("0002");
            msg.setMsg("未捕获到章节");
            return msg;
        }

        // 保存章节
//        saveItem(novelDto.getItems(), configDto, id, null);
        AddNovelThread addNovelThread = new AddNovelThread(novelDto.getItems(), configDto, id, null);
        new Thread(addNovelThread).start(); //启动添加小说线程

        GetNovelConfig oldConfig = GetNovelConfig.dao.findByUrl(configDto.getRootUrl());
        if (oldConfig == null) {
            GetNovelConfig config = new GetNovelConfig();
            config.put("webSiteName", configDto.getWebSiteName());
            config.put("url", configDto.getRootUrl());
            config.put("getIndexWayValue", configDto.getGetIndexWayValue());
            config.put("key", MyStringUtil.arrayToStr(configDto.getKey()));

            config.put("getImgWayValue", configDto.getGetImgWayValue());
            config.put("imgKey", MyStringUtil.arrayToStr(configDto.getImgKeys()));

            config.put("getNovelNameWayValue", configDto.getGetNovelNameWayValue());
            config.put("novelNameKeys", MyStringUtil.arrayToStr(configDto.getNovelNameKeys()));

            config.put("getAuthorWayValue", configDto.getGetAuthorWayValue());
            config.put("novelAuthorKeys", MyStringUtil.arrayToStr(configDto.getNovelAuthorKeys()));

            config.put("getDescWayValue", configDto.getGetDescWayValue());
            config.put("novelDescKeys", MyStringUtil.arrayToStr(configDto.getNovelDescKeys()));
            config.put("descKey2Index", configDto.getDescKey2Index());

            config.put("getContentWayValue", configDto.getGetContentWayValue());
            config.put("itemKey", configDto.getItemKey());

            config.put("createTime", new Date());

            config.save();

        }

        msg.setCode("0000");
        msg.setMsg("success");

        return msg;
    }

    public static void saveItem(List<NovelItemDto> itemDtos, GetNovelConfigDto configDto, Integer novelId, NovelItem lastItem) {
        for (int i = 0; i < itemDtos.size(); i++) {
            NovelItemDto itemDto = itemDtos.get(i);
            logger.info(logPre + "saveItem,url:" + itemDto.getUrl());
//            String itemContent = configDto.getGetContentWay().getContent(itemDto.getUrl(), configDto.getItemKey());

            NovelItem novelItem = new NovelItem();
            novelItem.put("url", itemDto.getUrl());
            novelItem.put("norvelId", novelId);
            novelItem.put("title", itemDto.getItemName());
//            novelItem.put("content", itemContent);
            novelItem.put("createTime", new Date());

            if (lastItem != null) {
                novelItem.put("preId", lastItem.get("id"));
            }
            novelItem.save();

            if (lastItem != null) {
                lastItem.set("nextId", novelItem.get("id"));

                lastItem.update();
            }

            lastItem = novelItem;
        }
    }

    public Msg refresh(Integer id) {

        Msg msg = new Msg();
        NovelItem item = NovelItem.dao.findById(id).toBean();
        if (item == null) {
            msg.setCode("0110");
            msg.setMsg("未找到章节,参数有误");
            return msg;
        }

        String url = item.getUrl();

        String urlRoot = JsoupUtil.getUrlRoot(url);

        GetNovelConfig config = GetNovelConfig.dao.findByUrl(urlRoot);
        if (config == null) {
            msg.setCode("0111");
            msg.setMsg("未找到配置,参数有误");

            item.set("remark", "未找到配置,参数有误");
            item.set("createTime", new Date());
            item.update();

            return msg;
        }

        GetNovelConfigDto configDto = new GetNovelConfigDto(config);
//        configDto.setItemKey(config.get("itemKey") + "");

        String itemContent = configDto.getGetContentWay().getContent(url, configDto.getItemKey());
        if (itemContent == null || itemContent.trim().equals("") || itemContent.length() < 100) {
            itemContent = configDto.getGetContentWay2().getContent(url, configDto.getItemKey());
        }

        if (itemContent == null || itemContent.trim().equals("")) {
            msg.setCode("0112");
            msg.setMsg("未抓到内容");

            item.set("remark", "未抓到内容");
            item.set("createTime", new Date());
            item.update();
            return msg;
        }

//        item.set("content", itemContent);
        String configFilePath = MyConstants.novelBasePath + item.getNorvelId() + "/" + item.getId() + ".txt";
        boolean result = FileUtil.saveFile(itemContent, configFilePath);
        if (result) {
            item.set("contentFile", configFilePath);
            int length = itemContent.length();
            item.set("file_length", length);
            item.update();

            msg.setCode("0000");
            msg.setMsg("success");
        } else {
            item.set("remark", "save content to File fail");
            item.update();

            msg.setCode("1000");
            msg.setMsg("save content to File fail");
        }

        return msg;
    }

    public void update(Integer id) {
        Novel novelOld = Novel.dao.findById(id);
        List<NovelItem> novelItems = novelOld.getNovelItems();
        NovelItem lastItemDto = null;
        if (novelItems != null && !novelItems.isEmpty()) {
            lastItemDto = novelItems.get(novelItems.size() - 1);
        }

        String indexUrl = novelOld.get("url");

        if (indexUrl == null) {
            logger.info(logPre + "没有目录url略过:" + ModelUtil.modelToMap(novelOld));
            return;
        }

        GetNovelConfigDto getNovelConfigDto = this.getConfigByIndexUrl(indexUrl);

        if (getNovelConfigDto == null) {
            return;
        }

        getNovelConfigDto.setIndexUrl(indexUrl);
        logger.info("novelConfigDto:" + getNovelConfigDto);

        NovelDto novelDto = JsoupUtil.getNovel(getNovelConfigDto);

        if (novelDto == null || novelDto.getItems() == null || novelDto.getItems().isEmpty()) {
            logger.info(logPre + "未获取取到章节" + ModelUtil.modelToMap(novelOld));
            return;
        }

        List<NovelItemDto> items = novelDto.getItems();

        //找到最后更新章节位置
        int lastIndex = -1;
        //从最后开始找起
        for (int i = items.size() - 1; i >= 0; i--) {
            String url = items.get(i).getUrl();
            if (lastItemDto != null && url.equals(lastItemDto.get("url"))) {
                lastIndex = i;
                break;
            }
        }

        logger.info(logPre + "update lastIndex:" + lastIndex);
        //更新章节
        if (items != null && lastIndex < items.size() - 1) {
            List<NovelItemDto> updateItemList = items.subList(lastIndex + 1, items.size());
//            NovelItem lastItem;
//            lastItem = lastItemDto != null ? NovelItem.dao.findById(lastItemDto.get("id")) : null;
            if (lastItemDto != null && lastIndex == -1) {
                //如果本来有章节，但是却找不到匹配的，那就不要去更新
                logger.warn("未找到匹配的最后一个章节，怀疑是没有下载到完整的目录页");
                return;
            }

            if (updateItemList.size() > 60) {
                logger.info("非常规更新->last:" + lastItemDto + ", list:" + updateItemList);
            }

            saveItem(updateItemList, getNovelConfigDto, id, lastItemDto);
        }

    }

    /**
     * 提供保存章节内容的方法
     *
     * @param itemId
     * @param content
     * @return
     */
    public Msg updateContent(Integer itemId, String content) {

        Msg msg = new Msg();
        NovelItem item = NovelItem.dao.findById(itemId).toBean();
        if (item == null) {
            msg.setCode("0110");
            msg.setMsg("未找到章节,参数有误");
            return msg;
        }

        if (content == null || content.trim().equals("")) {
            msg.setCode("0112");
            msg.setMsg("未抓到内容");

            return msg;
        }

        String configFilePath = MyConstants.novelBasePath + item.getNorvelId() + "/" + item.getId() + ".txt";
        boolean result = FileUtil.saveFile(content, configFilePath);
        if (result) {
            item.set("contentFile", configFilePath);
            item.update();

            msg.setCode("0000");
            msg.setMsg("success");
        } else {
            item.set("remark", "save content to File fail");
            item.update();

            msg.setCode("1000");
            msg.setMsg("save content to File fail");
        }

        return msg;
    }

    public GetNovelConfigDto getConfigByIndexUrl(String indexUrl) {
        String urlRoot = JsoupUtil.getUrlRoot(indexUrl);

        GetNovelConfig configEntity = GetNovelConfig.dao.findByUrl(urlRoot);

        if (configEntity == null) {
            return null;
        }

        GetNovelConfigDto novelConfigDto = new GetNovelConfigDto(configEntity);

        return novelConfigDto;
    }

    public String download(Integer novelId, String fileType, HttpServletResponse response) throws IOException {

        if (fileType == null || fileType.trim().equals("")) {
            fileType = "txt";
        }

        List<Map<String, Object>> undownloadNovelItems = Novel.dao.getUndownloadNovelItems();

        if (!undownloadNovelItems.isEmpty()) {
//            response.getOutputStream().println("novel is not all download");

            return "not download";
        }

        Novel novel = Novel.dao.findById(novelId);

        List<NovelItem> novelItems = novel.getNovelItemsWithContent();
        if (novelItems.isEmpty()) {
//            response.getOutputStream().println("novelItem is empty");
            return "no content";
        }

        String contentType = "application/x-msdownload";
        String enc = "utf-8";
        String fileName = novel.getStr("novelName");
        String filename = URLEncoder.encode(fileName + "." + fileType, enc);

        response.reset();
        response.setContentType(contentType);
        response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        /*创建输出流*/
        ServletOutputStream servletOS = response.getOutputStream();

//        servletOS.write(novel.getStr("novelName").getBytes("utf-8"));
//        
//        servletOS.write(("\t作者：" + novel.getStr("author")).getBytes("utf-8"));
//        servletOS.write(("\t简介：" + novel.getStr("desc")).getBytes("utf-8"));
        if ("pdf".equalsIgnoreCase(fileType)) {
            this.wirtePdf(servletOS, novelItems);
        } else {
            servletOS.write(novel.getStr("novelName").getBytes("utf-8"));

            servletOS.write(("\t作者：" + novel.getStr("author")).getBytes("utf-8"));
            servletOS.write(("\t简介：" + novel.getStr("desc")).getBytes("utf-8"));
            this.writeTxt(servletOS, novelItems);
        }

//        for (NovelItem item : novelItems) {
//            String title = item.get("title") + "";
//            String content = MyStringUtil.toContentString("" + item.getContent());
//            servletOS.write(("\n\n" + title + "\n\n\n ").getBytes("utf-8"));
//            servletOS.write(content.getBytes("utf-8"));
//        }
        servletOS.flush();
        servletOS.close();
        return "";
    }

    private void writeTxt(ServletOutputStream servletOS, List<NovelItem> novelItems) throws IOException {
        for (NovelItem item : novelItems) {
            String title = item.get("title") + "";
            String content = MyStringUtil.toContentString("" + item.getContent());
            servletOS.write(("\n\n" + title + "\n\n\n ").getBytes("utf-8"));
            servletOS.write(content.getBytes("utf-8"));
        }
    }

    public void wirtePdf(ServletOutputStream servletOS, List<NovelItem> novelItems) {
        try {
            PdfUtil.outputPdf(servletOS, novelItems);
        } catch (Exception ex) {
            logger.error(logPre + ex.getMessage(), ex);
        }
    }

    public Msg delete(Integer[] novelIds) {

        Msg msg = new Msg();

        try {
            for (Integer novelId : novelIds) {
                NovelItem.dao.deleteByNovelId(novelId);
                boolean deleteResult = Novel.dao.deleteById(novelId);
                if (deleteResult) {
                    String dirPath = MyConstants.novelBasePath + novelId + "/";
                    FileUtil.deleteDir(dirPath);
                }
            }
        } catch (Exception e) {
            msg.setMsg(e.getMessage());
            return msg;
        }

        msg.setCode("0000");
        msg.setMsg("success");
        return msg;
    }

    /**
     * 从数据删除章节，从磁盘上删除章节内容
     *
     * @param novelItemIds
     * @return
     */
    public Msg deleteItems(Integer[] novelItemIds) {

        Msg msg = new Msg();

        try {

            for (Integer itemId : novelItemIds) {

                NovelItem item = NovelItem.dao.findById(itemId);

                Integer norvelId = item.get("norvelId");
                String contentFile = item.get("contentFile");
                if (item.delete() && contentFile != null && !contentFile.trim().equals("")) {
                    String filePath = MyConstants.novelBasePath + norvelId + "/" + itemId + ".txt";
                    FileUtil.deleteFile(filePath);
                }
            }
        } catch (Exception e) {
            msg.setMsg(e.getMessage());
            return msg;
        }

        msg.setCode("0000");
        msg.setMsg("success");
        return msg;
    }

    /**
     * 删除指定章节ID前的章节，从数据删除章节，从磁盘上删除章节内容
     *
     * @param novelItemIds
     * @return
     */
    public Msg deleteBeforeItems(Integer novelItemId) {

        Msg msg = new Msg();

        NovelItem novelItem = NovelItem.dao.findById(novelItemId).toBean();
        if (novelItem == null) {
            msg.setCode("0110");
            msg.setMsg("not found novelItem");
            return msg;
        }

        Novel novel = Novel.dao.findById(novelItem.getNorvelId());

        List<NovelItem> novelItems = novel.getNovelItems();

        try {

            for (NovelItem item : novelItems) {
                Integer id = item.getId();
                if (id >= novelItemId) {
                    break;
                }
//                NovelItem item = NovelItem.dao.findById(itemId);
                Integer itemId = item.getId();
                Integer norvelId = item.get("norvelId");
                String contentFile = item.get("contentFile");
                if (item.delete() && contentFile != null && !contentFile.trim().equals("")) {
                    String filePath = MyConstants.novelBasePath + norvelId + "/" + itemId + ".txt";
                    FileUtil.deleteFile(filePath);
                }
            }
        } catch (Exception e) {
            msg.setMsg(e.getMessage());
            return msg;
        }

        msg.setCode("0000");
        msg.setMsg("success");
        return msg;
    }
}
