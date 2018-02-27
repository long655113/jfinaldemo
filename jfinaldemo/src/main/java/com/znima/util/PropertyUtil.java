/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class PropertyUtil {
    
    private static Properties pro;
    
    private static Properties getProp() {
        
        if (pro == null) {
            try {
                pro = new Properties();
                InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
                pro.load(in);
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(PropertyUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return pro;
    }
    
    public static String getValue(String key) {
        String value = getProp().getProperty(key);
        
        return value;
    }
    
}
