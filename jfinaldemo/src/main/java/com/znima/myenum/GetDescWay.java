/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.myenum;

import com.znima.dto.GetNovelConfigDto;
import com.znima.util.JsoupUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 查找小说简介方式
 *
 * @author Administrator
 */
public enum GetDescWay {

    ID(1, "id") {

                @Override
                public String getNovelDesc(Document doc, GetNovelConfigDto config) {
                    String desc = null;
                    try {
                        Element descEles = doc.getElementById(config.getNovelDescKeys()[0]);

                        Elements novelDescEles = descEles.getElementsByTag(config.getNovelDescKeys()[1]);

                        Integer descKey2Index = config.getDescKey2Index();
                        Element descEle = novelDescEles.get(descKey2Index);
                        desc = descEle.text();

                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
                    }

                    return desc;
                }

            }, CLASS(2, "class") {

                @Override
                public String getNovelDesc(Document doc, GetNovelConfigDto config) {
                    String desc = null;
                    try {
                        
                        String[] descKeys = config.getNovelDescKeys();
                        Elements infoEles = doc.getElementsByClass(descKeys[0]);
                        
                        if (descKeys.length == 1) {
                            Element descEle = infoEles.get(config.getDescKey2Index());
                            desc = descEle.text();
                        } else {
                            Element infoEle = infoEles.first();

                            Elements novelDescEles = infoEle.getElementsByTag(descKeys[1]);

                            Integer descKey2Index = config.getDescKey2Index();
                            Element descEle = novelDescEles.get(descKey2Index);
                            desc = descEle.text();
                        }
                        

                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取简介错误", e);
                    }

                    return desc;
                }

            }, OTHER(3, "other") {

                @Override
                public String getNovelDesc(Document doc, GetNovelConfigDto config) {
                    String desc = null;
                    try {
                        Element descEles = doc.getElementById(config.getNovelDescKeys()[0]);

                        Elements novelDescEles = descEles.getElementsByTag(config.getNovelDescKeys()[1]);

                        Integer descKey2Index = config.getDescKey2Index();
                        Element descEle = novelDescEles.get(descKey2Index);
                        desc = descEle.text();

                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
                    }

                    return desc;
                }

            }, META(4, "meta") {
                
                @Override
                public String getNovelDesc(Document doc, GetNovelConfigDto config) {
                    String desc = null;
                    try {
                        String[] keys = config.getNovelDescKeys();
                        Elements metaEles = doc.getElementsByAttributeValue(keys[0], keys[1]);
                        Element metaAuthor = metaEles.first();
                        
                        desc = metaAuthor.attr("content");
                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取简介错误", e);
                    }

                    return desc;
                }
            };
    private int value;
    private String name;

    private GetDescWay(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public static GetDescWay getIndexWayBayValue(int value) {
        
        for (GetDescWay way : GetDescWay.values()) {
            if (way.value == value) {
                return way;
            }
        }
        return null;
    }

    public String getNovelDesc(Document doc, GetNovelConfigDto config) {
        String desc = null;
        try {
            Element descEles = doc.getElementById(config.getNovelDescKeys()[0]);
            
            Elements novelDescEles = descEles.getElementsByTag(config.getNovelDescKeys()[1]);
            
            Integer descKey2Index = config.getDescKey2Index();
            Element descEle = novelDescEles.get(descKey2Index);
            desc = descEle.text();
            
        } catch (Exception e) {
            Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
        }
        
        return desc;
    }

}
