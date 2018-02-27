/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

/**
 *
 * @author Administrator
 */
public class MyStringUtil {
    public static String arryToString(Object[] objs) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(arrayToStr(objs));
        sb.append(']');
        
        return sb.toString();
    }
    
    public static String arrayToStr(Object[] objs) {
        if (objs == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            sb.append(obj).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        
        return sb.toString();
    }
    
    public static String toContentString(String src) {
        String dist = src.replaceAll("<br/>", "\n")
                .replaceAll("<br />", "\n")
                .replaceAll("<br>", "\n")
                .replaceAll("&nbsp;", " ")
                .replaceAll("<div>", "")
                .replaceAll("</div>", "");
        return dist;
    }
}
