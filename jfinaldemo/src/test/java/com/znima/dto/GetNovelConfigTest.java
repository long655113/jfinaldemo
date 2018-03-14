/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.dto;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class GetNovelConfigTest {
    
    Logger logger = LoggerFactory.getLogger(GetNovelConfigTest.class);
    
    @Test
    public void test() throws Exception {
        String[] novelNameKeys = {"info", "h1"};
        String[] key = {"list"};
        String[] getIndexWay = {"1"};
        
        Map<String, String[]> params = new HashMap();
        params.put("novelNameKeys", novelNameKeys);
        params.put("key", key);
        params.put("getIndexWay", getIndexWay);
        
        GetNovelConfigDto novelConfig = new GetNovelConfigDto(params);
        
        logger.info("novelConfig:" + novelConfig);
    }

}
