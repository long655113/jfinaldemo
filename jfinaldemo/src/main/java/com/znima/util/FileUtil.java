/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class FileUtil {

    /**
     * 读取文件内容
     * @param filePath
     * @return 
     */
    public static String getFileContent(String filePath) {
        if (filePath == null) {
            return null;
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            StringBuilder sb = new StringBuilder();

            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }

            br.close();
            return sb.toString();
        } catch (Exception ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 保存内容到目标文件
     * @param content
     * @param filePath
     * @return 
     */
    public static boolean saveFile(String content, String filePath) {
        try {

            
            String dir = filePath.substring(0, filePath.lastIndexOf("/"));
            
            File file = new File(dir);
            
            if (!file.exists()) {
                file.mkdirs();
            }
            
            PrintWriter pw = new PrintWriter(filePath, "utf-8");

            pw.println(content);

            pw.flush();
            pw.close();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    /**
     * 删除目录
     * @param dirPath
     * @return 
     */
    public static boolean deleteDir(String dirPath) {
        File file = new File(dirPath);
            
        if (!file.exists()) {
            return true;    //目录不存在就算是成功删除
        }
        
        File[] listFiles = file.listFiles();
        for (File itemFile : listFiles) {
            itemFile.delete();
        }
        file.delete();
        
        return true;
    }
    
    /**
     * 删除文件
     * @param filePath
     * @return 
     */
    public static boolean deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
