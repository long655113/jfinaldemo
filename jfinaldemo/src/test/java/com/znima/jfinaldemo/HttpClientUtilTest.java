/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.jfinaldemo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class HttpClientUtilTest { 
    
    Logger logger = LoggerFactory.getLogger(HttpClientUtilTest.class);
    
//    @Test  
//    public void testSendHttpPost1() {  
//        String responseContent = HttpClientUtil.getInstance()  
//                .sendHttpPost("http://localhost:8089/test/send?username=test01&password=123456");  
//        logger.info("reponse content:" + responseContent);
//    }  
//      
//    @Test  
//    public void testSendHttpPost2() {  
//        String responseContent = HttpClientUtil.getInstance()  
//                .sendHttpPost("http://localhost:8089/test/send", "username=test01&password=123456");  
//        logger.info("reponse content:" + responseContent);  
//    }  
//      
//    @Test  
//    public void testSendHttpPost3() {  
//        Map<String, String> maps = new HashMap<String, String>();  
//        maps.put("username", "test01");  
//        maps.put("password", "123456");  
//        String responseContent = HttpClientUtil.getInstance()  
//                .sendHttpPost("http://localhost:8089/test/send", maps);  
//        logger.info("reponse content:" + responseContent);  
//    }  
//    @Test  
//    public void testSendHttpPost4() {  
//        Map<String, String> maps = new HashMap<String, String>();  
//        maps.put("username", "test01");  
//        maps.put("password", "123456");  
//        List<File> fileLists = new ArrayList<File>();  
//        fileLists.add(new File("D://test//httpclient//1.png"));  
//        fileLists.add(new File("D://test//httpclient//1.txt"));  
//        String responseContent = HttpClientUtil.getInstance()  
//                .sendHttpPost("http://localhost:8089/test/sendpost/file", maps, fileLists);  
//        logger.info("reponse content:" + responseContent);  
//    }  
//  
//    @Test  
//    public void testSendHttpGet() {  
//        String responseContent = HttpClientUtil.getInstance()  
//                .sendHttpGet("http://localhost:8089/test/send?username=test01&password=123456");  
//        logger.info("reponse content:" + responseContent);  
//    }  
//      
//    @Test  
//    public void testSendHttpsGet() {  
//        String responseContent = HttpClientUtil.getInstance()  
//                .sendHttpsGet("https://www.baidu.com");  
//        logger.info("reponse content:" + responseContent);  
//    }
//    
//    
//      
//    @Test  
//    public void testSendHttpsGetSuiment() {  
//        String responseContent = HttpClientUtil.getInstance("GBK")  
//                .sendHttpsGet("http://www.lread.net/read/257/");  
//        logger.info("reponse content:" + responseContent);
//        
//        int begin = responseContent.indexOf("<dt>正文</dt>");
//        String str2 = responseContent.substring(begin);
//        int end = str2.indexOf("</dl>");
//        String content = str2.substring(0, end);
//        
//        String[] split = content.split("<dd><a");
//        
//        List<String> asList = Arrays.asList(split);
//        
//        List<String> urlList = new ArrayList();
//        
//        for (String text : asList) {
//            int beginHref = text.indexOf("href=\"");
//            
//            if (beginHref == -1) {
//                logger.info("not find :{" + text + "}");
//                continue;
//            }
//            
//            text = text.substring(beginHref + 6);
//            int endHref = text.indexOf("\"");
//            
//            if (endHref == -1) {
//                logger.info("not find :{" + text + "}");
//                continue;
//            }
//            
//            String href = text.substring(0, endHref);
//            logger.info("href:" + href.replace("read", "book"));
//            urlList.add("http://m.lread.net" + href.replace("read", "book"));
//        }
//        
//        logger.info("result:" + urlList);
//        
//    }
}
