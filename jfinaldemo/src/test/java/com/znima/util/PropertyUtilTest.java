/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class PropertyUtilTest {
    
    Logger logger = LoggerFactory.getLogger(PropertyUtilTest.class);

    @Test
    public void testGetValue() {
        String value = PropertyUtil.getValue("content.base.path");
        logger.info("value:" + value);
    }
    
}
