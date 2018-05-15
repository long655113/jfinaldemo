/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import com.znima.dto.GetNovelConfigDto;
import com.znima.myenum.GetNovelNameWay;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class JsoupUtilTest {
    
    Logger logger = LoggerFactory.getLogger(JsoupUtilTest.class);
    
    public JsoupUtilTest() {
    }

    @Test
    public void testGetItemContent() {
        String url = "http://www.lread.net/read/36620/15848349.html";
        GetNovelConfigDto config = new GetNovelConfigDto();
        config.setItemKey("booktext");
        config.setGetContentWayValue(1);
        String itemContent = config.getGetContentWay().getContent(url, config.getItemKey());
        logger.info("itemContent:" + itemContent);
    }
    
    @Test
    public void testGetItemContent2() {
        String url = "http://www.8dushu.com/xiaoshuo/56/56522/14468464.html";
        GetNovelConfigDto config = new GetNovelConfigDto();
        config.setItemKey("yd_text2");
        config.setGetContentWayValue(2);
        String itemContent = config.getGetContentWay().getContent(url, config.getItemKey());
        logger.info("itemContent:" + itemContent);
    }
    
    @Test
    public void testGetItemContent3() {
        String url = "http://www.biquge5200.com/2_2599/1854656.html";
        GetNovelConfigDto config = new GetNovelConfigDto();
        config.setItemKey("content");
        config.setGetContentWayValue(1);
        String itemContent = config.getGetContentWay().getContent(url, config.getItemKey());
        logger.info("itemContent:" + itemContent);
    }
    
    @Test
    public void testGetItemContentBiquge() {
        String url = "http://www.biquge5200.com/2_2599/1847766.html";
        GetNovelConfigDto config = new GetNovelConfigDto();
        config.setItemKey("content");
        config.setGetContentWayValue(1);
        String itemContent = config.getGetContentWay().getContent(url, config.getItemKey());
//        logger.info("itemContent:" + itemContent);
        String content = itemContent.replaceAll("<br/>", "\n")
                .replaceAll("<br />", "\n")
                .replaceAll("<br>", "\n")
                .replaceAll("&nbsp;", " ");
        
        logger.info("content:" + content);
    }
    
    @Test
    public void testDownloadImg() throws FileNotFoundException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Documents\\test\\img.txt")));
        
        StringBuilder sb = new StringBuilder();
        
        String readLine;
        
        while ((readLine = br.readLine()) != null) {
            sb.append(readLine).append("\n");
        }
        
        Document inEle = Jsoup.parse(sb.toString());
        
        InputStream in = JsoupUtil.downLoadImg(inEle);
        
        OutputStream os = new FileOutputStream(new File("C:\\Users\\Administrator\\Documents\\test", "597048.gif"));
        byte[] buf = new byte[1024];
        int l=0;
        while((l = in.read(buf))!=-1){
            os.write(buf, 0, l);
        }
    }
    
    @Test
    public void testGetAuthor() {
         String url = "http://www.55dushu.com/book/96/96696/";
        GetNovelConfigDto config = new GetNovelConfigDto();
        config.setGetNovelNameWay(GetNovelNameWay.CLASS);
        config.setGetNovelNameWayValue(2);
        String[] novelNameKeys = {"mu_h1", "h1"};
        config.setNovelNameKeys(novelNameKeys);
        
        String novelName = config.getGetNovelNameWay().getNovelName(JsoupUtil.getDoc(url), novelNameKeys);
        
        System.out.println("novelName:" + novelName);
    }
}
