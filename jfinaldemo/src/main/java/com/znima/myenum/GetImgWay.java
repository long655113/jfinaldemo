/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.myenum;

import com.znima.dto.GetNovelConfigDto;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 查找封面方式
 *
 * @author Administrator
 */
public enum GetImgWay {

    ID(1, "id") {

                @Override
                public String getImageUrl(Document doc, GetNovelConfigDto config) {
                    
                    String[] keys = config.getImgKeys();
                    Element imgaeEle = doc.getElementById(keys[0]);
                    Elements imageTag = imgaeEle.getElementsByTag("img");
                    Element img = imageTag.first();
                    String imageUrl = img.attr("src");
                    return imageUrl;
                }

            }, CLASS(2, "class") {

                @Override
                public String getImageUrl(Document doc, GetNovelConfigDto config) {
                    
                    String[] keys = config.getImgKeys();
                    Elements imgaeEles = doc.getElementsByClass(keys[0]);
                    if (imgaeEles == null) {
                        return null;
                    }
                    
                    Element imgaeEle = imgaeEles.first();
                    
                    Elements imageTag = imgaeEle.getElementsByTag("img");
                    Element img = imageTag.first();
                    String imageUrl = img.attr("src");
                    return imageUrl;
                }

            }, OTHER(3, "other") {

                @Override
                public String getImageUrl(Document doc, GetNovelConfigDto config) {
                    
                    String[] keys = config.getImgKeys();
                    Element imgaeEle = doc.getElementById(keys[0]);
                    Elements imageTag = imgaeEle.getElementsByTag("img");
                    Element img = imageTag.first();
                    String imageUrl = img.attr("src");
                    return imageUrl;
                }

            },meta(4, "meta") {
                
                @Override
                public String getImageUrl(Document doc, GetNovelConfigDto config) {
                    String imageUrl = null;
                    try {
                        String[] keys = config.getImgKeys();
                        Elements metaEles = doc.getElementsByAttributeValue(keys[0], keys[1]);
                        Element meta = metaEles.first();
                        
                        imageUrl = meta.attr("content");
                    } catch (Exception e) {
                        Logger.getLogger(GetImgWay.class.getName()).log(Level.SEVERE, "获取封面错误", e);
                    }

                    return imageUrl;
                }
            };
    private int value;
    private String name;

    private GetImgWay(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public static GetImgWay getIndexWayBayValue(int value) {
        
        for (GetImgWay way : GetImgWay.values()) {
            if (way.value == value) {
                return way;
            }
        }
        return null;
    }

    public String getImageUrl(Document doc, GetNovelConfigDto config) {
        return "";
    }

}
