/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.znima.dto.GetNovelConfigDto;
import com.znima.entity.GetNovelConfig;
import com.znima.entity.Novel;
import com.znima.entity.NovelItem;
import com.znima.result.Msg;
import com.znima.service.NovelService;
import com.znima.util.JsoupUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class NovelController extends Controller {
    
    private static Logger logger = LoggerFactory.getLogger(NovelController.class);
    
    private NovelService novelService = new NovelService();
    
    public void toAddNovel() {
        List<GetNovelConfig> configs = GetNovelConfig.dao.findNovelConfigs();
        setAttr("configs", configs);
        
        render("/page/nomalAddNovel.jsp");
    }
    
    /**
     * 普通添加小说（只输入目录url）
     */
    public void novelAddNovel() {
        String url = getPara("url");
        if (url == null || url.trim().equals("")) {
            forwardAction("/novel/toAddNovel");
            return;
        }
        
        String urlRoot = JsoupUtil.getUrlRoot(url);
        GetNovelConfig config = GetNovelConfig.dao.findByUrl(urlRoot);
        if (config == null) {
            logger.info("未找到网站配置");
            render("/page/addNovel.html");
            return;
        }
        
        GetNovelConfigDto novelConfigDto = new GetNovelConfigDto(config);
        novelConfigDto.setIndexUrl(url);
        Msg msg = this.novelService.addNovel(novelConfigDto);
        logger.info("novelAddNovel result:" + msg);
        
        forwardAction("/novel/novelList");
    }
    
    /**
     * 高级添加小说，添加详细配置
     * @throws Exception 
     */
    public void addNovel() throws Exception {
        
//        GetNovelConfig config = getBean(GetNovelConfig.class, "config");
        Map<String, String[]> paraMap = getParaMap();
        logger.info("params:" + paraMap);
        
//        logger.info("config:" + config);
        GetNovelConfigDto novelConfig = new GetNovelConfigDto(paraMap);
        
        Msg msg = novelService.addNovel(novelConfig);
        
        logger.info("result:" + msg);
        
        forwardAction("/novel/novelList");
    }
    
    public void novelList() {
        
        List<Novel> novels = this.novelService.getNovels();
        
        setAttr("novels", novels);
       
        render("/page/169/novelList.jsp");
    }
    
    public void novelIndex() {
        
        Novel novel = Novel.dao.findById(getParaToInt()).toBean();
        
        //分页查询参数
        Integer page = getParaToInt("page");   //默认每页10行
        String sortType = getPara("sortType");
        if (sortType == null || sortType.trim().equals("")) {
            sortType = "desc";
        }
        
        if (novel != null) {
            setAttr("novel", novel);
            Page<NovelItem> pageData = novel.getNovelItemsPage(page, sortType);
            
            List<Integer> pageNumbers = new ArrayList();
            for (int i = 1; i <= pageData.getTotalPage(); i++) {
                pageNumbers.add(i);
            }
            
            setAttr("pageNumbers", pageNumbers);
            setAttr("currentPage", pageData.getPageNumber());
            setAttr("novelItems", pageData.getList());
            setAttr("sortType", sortType);
            
            NovelItem lastestItem = novel.getLastestItem();
            
            setAttr("lastestItem", lastestItem);
        }
       
        render("/page/169/index.jsp");
    }
    
    public void read() {
        Integer id = getParaToInt();
        
        NovelItem novelItem = NovelItem.dao.findById(id).toBean();
        setAttr("novelItem", novelItem);
        render("/page/169/novelItem.jsp");
    }
    
    /**
     * 更新章节
     */
    public void update() {
        Integer id = getParaToInt();
        
        this.novelService.update(id);
        
        Novel novel = Novel.dao.findById(id).toBean();
        
        if (novel != null) {
            setAttr("novel", novel);
            List<NovelItem> novelItems = novel.getNovelItems();
            setAttr("novelItems", novelItems);
        }
       
        render("/page/169/index.jsp");
    }
    
    /**
     * 刷新，在添加小说明可能因为网络原因，部分章节内容未成功下载
     */
    public void refresh() {
        Integer id = getParaToInt();
        Msg msg = this.novelService.refresh(id);
        
        renderJson(msg);
    }
    
    public void download() {
        Integer novelId = getParaToInt();
        String fileType = getPara("fileType");
        HttpServletResponse response = getResponse();
        
        try {
            String result = this.novelService.download(novelId, fileType, response);
            if (!result.equals("")) {
                setAttr("result", result);
                render("/page/downloadResult.jsp");
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
        renderNull();
        return;
    }
    
    /**
     * 删除小说
     */
    public void delete() {
        Integer[] novelIds = getParaValuesToInt("novelId");
        
        Msg msg = this.novelService.delete(novelIds);
        
        renderJson(msg);
    }
    
    /**
     * 删除小说章节
     */
    public void deleteItem() {
        Integer[] novelItemIds = getParaValuesToInt("novelItemId");
        
        Msg msg = this.novelService.deleteItems(novelItemIds);
        
        renderJson(msg);
    }
    
    /**
     * 删除指定章节前的章节
     */
    public void deleteBeforeItem() {
        Integer novelItemId = getParaToInt("novelItemId");
        Msg msg = this.novelService.deleteBeforeItems(novelItemId);
        
        renderJson(msg);
    }
    
    /**
     * 上传章节内容
     */
    public void submitItemContent() {
        Integer itemId = getParaToInt("itemId");
        String content = getPara("content");
        
        if (itemId == null || itemId == 0 || content == null || content.trim().equals("")) {
            Msg msg = new Msg();
            msg.setMsg("参数错误");
            renderJson(msg);
            return;
        }
        
        Msg msg = this.novelService.updateContent(itemId, content);
        
        renderJson(msg);
    }
}
