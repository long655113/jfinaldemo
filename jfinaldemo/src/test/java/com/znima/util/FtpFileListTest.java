/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class FtpFileListTest {

    @Test
    public void dateTest() {
        long a = 1517010120l;
        Date date = new Date(a * 1000);

        //2018/1/27 上午7:42:00
        //yyyy/MM//dd aaahh:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd aaahh:mm:ss");

        System.out.println(sdf.format(date));
    }

    @Test
    public void showFileMsg() throws IOException {
        
        Path testPath = Paths.get("C:\\f\\Media/还我真情.mp3");
        BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicView.readAttributes();

        System.out.println("创建时间：" + new Date(basicFileAttributes.creationTime().toMillis()));

        System.out.println("最后访问时间：" + new Date(basicFileAttributes.lastAccessTime().toMillis()));

        System.out.println("最后修改时间：" + new Date(basicFileAttributes.lastModifiedTime().toMillis()));

        System.out.println("文件大小：" + basicFileAttributes.size());

        FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);

        System.out.println("文件所有者：" + ownerView.getOwner());
    }
    
    @Test
    public void createFtpFileMsg() throws IOException {
        //输出正面这样的字符
        //addRow("avatar", "avatar", 1, 0, "0 B", 1517010120, "2018/1/27 上午7:42:00");
//        Path testPath = Paths.get("C:\\f\\Media/还我真情.mp3");
        Path testPath = Paths.get("C:\\f\\Media");
        Path fileName = testPath.getFileName();
        
        BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicView.readAttributes();
        
        Date createTime = new Date(basicFileAttributes.creationTime().toMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd aaahh:mm:ss");
        String createTimeStr = sdf.format(createTime);
        
        StringBuilder sb = new StringBuilder();
        sb.append("<script>addRow(\"").append(fileName).append("\", \"").append(fileName).append("\", ");   //addRow("avatar", "avatar", 
        if (basicFileAttributes.isDirectory()) {
            sb.append(1);
            sb.append(", ").append(0);
            sb.append(", \"").append("0 B").append(", ")
                    .append(createTime.getTime() / 1000).append(", \"")
                    .append(createTimeStr).append("\");");
        } else {
            sb.append(0);
            long size = basicFileAttributes.size();
            
            sb.append(", ").append(size);
            
            //折算 GB，MB，KB
            String sizeMsg = size + " B";
            if (size > 1024*1024*1024) {
                sizeMsg = String.format("%.2f", size / (1024.0*1024*1024)) + " GB";
            } else if (size > 1024*1024) {
                sizeMsg = String.format("%.2f", size / (1024.0*1024)) + " MB";
            } else if (size > 1024) {
                sizeMsg = String.format("%.2f", size / 1024.0) + " kB";
            }
            
            sb.append(", \"").append(sizeMsg).append("\", ")
                    .append(createTime.getTime() / 1000).append(", \"")
                    .append(createTimeStr).append("\");");
        }
        sb.append("</script>");
        
        System.out.println("result: " + sb.toString());
    }
}
