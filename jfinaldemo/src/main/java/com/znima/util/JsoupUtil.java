/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import com.znima.dto.GetNovelConfigDto;
import com.znima.dto.NovelDto;
import com.znima.dto.NovelItemDto;
import com.znima.myenum.GetIndexWay;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ListIterator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class JsoupUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(JsoupUtil.class);
    
    public static Document getDoc(String url) {
        try {
            String urlRoot = getUrlRoot(url);
            
            Connection connect;
            
            if (url.startsWith("https")) {
                connect = HTTPCommonUtil.getConn(url, 7000);
            } else {
                connect = Jsoup.connect(url);
            }
            
            connect.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connect.header("Accept-Encoding","gzip, deflate, sdch");
            connect.header("Accept-Language","zh-CN,zh;q=0.8");
            connect.header("Cache-Control","max-age=0");
            connect.header("Connection","keep-alive");
            connect.header("Host",urlRoot);
            connect.header("Upgrade-Insecure-Requests","1");
            connect.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36");
            Document doc = connect.timeout(7000).get();
            return doc;
        } catch (IOException ex) {
            logger.error("访问" + url + "失败:" + ex.getMessage(), ex);
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        
        JsoupUtil.test();
        
        if (true) {
            return;
        }
        
        String requestUrl = "http://www.wenxuemi.com/files/article/html/0/169/";
        Document doc = getDoc(requestUrl);
        
        String url1 = requestUrl.replace("http://", "").replace("http://", "");
        int end = url1.indexOf("/");
        String rootUrl = url1.substring(0, end);
        
        if (doc == null) {
            logger.info("connent error");
            return;
        }
        
        String[] key = {"list"};
        Element chapterEle = GetIndexWay.ID.getIndexEle(doc, key);
        
//        Element chapterEle = doc.getElementById("list");
        
        if (chapterEle == null) {
            logger.info("chapterEle null");
            return;
        }
        Elements indexA = chapterEle.getElementsByTag("a");
        ListIterator<Element> listIterator = indexA.listIterator();
        while (listIterator.hasNext()) {
            Element a = listIterator.next();
            String url = a.attr("href");
            logger.info("url:" + rootUrl + url);
        }
        
    }
    
    private static void test() {
        GetNovelConfigDto config = new GetNovelConfigDto();
        
        config.setGetIndexWayValue(1);
        config.setIndexUrl("http://www.wenxuemi.com/files/article/html/0/169/");
        String[] key = {"list"};
        config.setKey(key);
        
        config.setImgKeys(new String[]{"fmimg"});
        
         //小说名
        String[] novelNameKeys = {"info", "h1"};
        config.setNovelNameKeys(novelNameKeys);
        
        //作者
        String[] novelAuthorKeys = {"info", "p"};
        config.setNovelAuthorKeys(novelAuthorKeys);
        
        //简介
        String[] novelDescKeys = {"intro", "p"};
        config.setNovelDescKeys(novelDescKeys);
        
        NovelDto novel = getNovel(config);
        
        logger.info("novel:" + novel);
    }
    
    /**
     * 获取小说信息及章节url
     * @param config
     * @return 
     */
    public static NovelDto getNovel(GetNovelConfigDto config) {
        
        String indexUrl = config.getIndexUrl();
        
        Document doc = getDoc(indexUrl);
        
        NovelDto novelDto = new NovelDto();
        novelDto.setIndexUrl(indexUrl);
        
        String rootUrl = getUrlRoot(indexUrl);
        
        config.setRootUrl(rootUrl);
        
        if (doc == null) {
            logger.info("connent error");
            return null;
        }
        
        String[] keys = config.getKey();
        Element chapterEle = config.getWay().getIndexEle(doc, keys);
        
        if (chapterEle == null) {
            logger.info("chapterEle null");
            return null;
        }
        Elements indexA = chapterEle.getElementsByTag("a");
        ListIterator<Element> listIterator = indexA.listIterator();
        
        if (!indexUrl.endsWith("/")) {
            indexUrl = indexUrl.substring(0, indexUrl.lastIndexOf("/") + 1);
        }
        
        String lastUrl = null;
        while (listIterator.hasNext()) {
            Element a = listIterator.next();
            String url = a.attr("href");
//            logger.info("url:" + rootUrl + url);
            NovelItemDto item = new NovelItemDto();
            item.setItemName(a.text());
            if (url.startsWith("/")) {
                url = rootUrl + url;
            } else if (url.toLowerCase().startsWith("http")) {
                
            } else {
                url = indexUrl + url;
            }
            item.setUrl(url);
            
            if (lastUrl == null) {
                lastUrl = url;
            } else {
                if (lastUrl.equals(url)) {
                    continue;
                } else {
                    lastUrl = url;
                }
            }
            
            novelDto.getItems().add(item);
        }
        
        //封面图片
        String imageUrl = config.getGetImgWay().getImageUrl(doc, config);
        
        if (imageUrl == null) {
            imageUrl = "http://ztd00.photos.bdimg.com/ztd/w=700;q=50/sign=f83997a3963df8dca63d8d91fd2a03b6/21a4462309f7905210d4de6a05f3d7ca7acbd59f.jpg";
        } else if (imageUrl.startsWith("/")) {
            imageUrl = rootUrl + imageUrl;
        }
        
        novelDto.setImage(imageUrl);
        
        //小说名
        String novelName = config.getGetNovelNameWay().getNovelName(doc, config.getNovelNameKeys());
        novelDto.setNovelName(novelName);
        
        //作者
        String author = config.getGetAuthorWay().getNovelAuthor(doc, config.getNovelAuthorKeys());
        novelDto.setAuthor(author);
        
        //简介
        String desc = config.getGetDescWay().getNovelDesc(doc, config);
        novelDto.setDesc(desc);
        
        return novelDto;
    }
    
    public static InputStream downLoadImg(Element inEle) {
        
        Elements imgTags = inEle.getElementsByTag("img");
        if (imgTags.isEmpty()) {
            logger.warn("not found img Tag->" + inEle.html());
            return null;
        }
        
        String imgUrl = imgTags.first().attr("src");
        
        // 截取图片文件名
        String fileName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1, imgUrl.length());

        try {
            // 文件名里面可能有中文或者空格，所以这里要进行处理。但空格又会被URLEncoder转义为加号
            String urlTail = URLEncoder.encode(fileName, "UTF-8");
            // 因此要将加号转化为UTF-8格式的%20
            imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('/') + 1) + urlTail.replaceAll("\\+", "\\%20");

        } catch (UnsupportedEncodingException e) {
            logger.error("eror->" + imgUrl + ", " + e.getMessage(), e);
        }

        try {
            // 获取图片URL
            URL url = new URL(imgUrl);
            // 获得连接
            URLConnection connection = url.openConnection();
            // 设置10秒的相应时间
            connection.setConnectTimeout(10 * 1000);
            // 获得输入流
            InputStream in = connection.getInputStream();
            
            return in;
        } catch (IOException e) {
            logger.error("downloadImg eror->" + imgUrl + ", " + e.getMessage(), e);
        }
        
        return null;
    }
    
    public static String getUrlRoot(String srcUrl) {
        String url1 = srcUrl.replace("https://", "").replace("http://", "");
        int end = url1.indexOf("/");
        String rootUrl = url1.substring(0, end);
        
        if (srcUrl.startsWith("https")) {
            rootUrl = "https://" + rootUrl;
        } else {
            rootUrl = "http://" + rootUrl;
        }
        
        return rootUrl;
    }
    
//    public static String getItemContent(String url, GetNovelConfigDto config) {
//        
//        String content = null;
//        try {
//            Document root = getDoc(url);
//            
//            //如果访问失败，再试3次
//            if (root == null) {
//                for (int i = 0; i < 3; i++) {
//                    root = getDoc(url);
//                }
//            }
//            
//            String itemKey = config.getItemKey();
//            
//            Element contentEle = root.getElementById(itemKey);
//            
//            content = contentEle.html();
//        } catch (Exception e) {
//            Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "解释章节内容失败", e);
//        }
//        
//        return content;
//    }
}
