/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.demo;

import com.znima.db.H2Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class FileDemo {
    
    Logger logger = LoggerFactory.getLogger(FileDemo.class);
    
    /**
     * 文件阅读
     */
    @Test
    public void fileRead() throws FileNotFoundException {
        File file = new File("c:/d/dd.txt");
        FileInputStream fIn = new FileInputStream(file);
        
        
    }
    
    @Test
    public void fileWrite() throws IOException {
        InputStream is = FileDemo.class.getClassLoader().getResourceAsStream("jobs.properties");
        
        Properties properties = new Properties();
        properties.load(is);
        
        int i =2;
        int b = 4;
        int a = 1 + (i++)+b+++(i++);
        
        logger.info("a:" + a + ", b:" + b + ", i:" + i);
        String group = properties.getProperty("job.channel.group");
        
        logger.info(group);
        
        
        if (true) {
            return;
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String readLine = br.readLine();
        logger.info(readLine);
    }
    
    
    
    /**
     * 文件读乱码
     */
    @Test
    public void fileEncode() {
        com.alibaba.druid.pool.DruidDataSource a;
    }
    
    /**
     * 反射：通过类路径获取类
     */
    @Test
    public void reflect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Class<?> classObject = Class.forName("com.znima.dto.NovelDto");
        
        Map<String, Class<?>> map = new HashMap();
        
        map.put("com.znima.dto.NovelDto", classObject);
        
        map.get("com.znima.dto.NovelDto").newInstance();
        
        Object obj = classObject.newInstance();
        String name = obj.getClass().getName();
        
        logger.info("clas name:" + name);
        
        Method setDescM = obj.getClass().getDeclaredMethod("setDesc", String.class);
        
        setDescM.invoke(obj, "abc");
        
        logger.info("toString:" + obj.toString());
        
//        String name2 = NovelDto.class.getName();
//        logger.info("name2:" + name2);
    }
    
}
