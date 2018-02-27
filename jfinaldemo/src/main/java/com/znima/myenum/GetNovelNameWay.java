/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.myenum;

import com.znima.util.JsoupUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 查找小说名方式
 *
 * @author Administrator
 */
public enum GetNovelNameWay {

    ID(1, "id") {

                @Override
                public String getNovelName(Document doc, String[] keys) {
                    String novelName = null;
                    try {
                        Element infoEle = doc.getElementById(keys[0]);

                        Elements novelNameEles = infoEle.getElementsByTag(keys[1]);

                        Element novelNameEle = novelNameEles.first();
                        novelName = novelNameEle.text();
                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取小说名错误", e);
                    }

                    return novelName;
                }

            }, CLASS(2, "class") {

                @Override
                public String getNovelName(Document doc, String[] keys) {
                    String novelName = null;
                    try {
                        Elements infoEles = doc.getElementsByClass(keys[0]);

                        Element infoEle = infoEles.first();

                        Elements novelNameEles = infoEle.getElementsByTag(keys[1]);

                        Element novelNameEle = novelNameEles.first();
                        novelName = novelNameEle.text();
                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取小说名错误", e);
                    }

                    return novelName;
                }

            }, OTHER(3, "other") {

                @Override
                public String getNovelName(Document doc, String[] keys) {
                    String novelName = null;
                    try {
                        Element infoEle = doc.getElementById(keys[0]);

                        Elements novelNameEles = infoEle.getElementsByTag(keys[1]);

                        Element novelNameEle = novelNameEles.first();
                        novelName = novelNameEle.text();
                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取小说名错误", e);
                    }

                    return novelName;
                }

            },meta(4, "meta") {
                
                @Override
                public String getNovelName(Document doc, String[] keys) {
                    String novelName = null;
                    try {
                        
                        Elements metaEles = doc.getElementsByAttributeValue(keys[0], keys[1]);
                        Element meta = metaEles.first();
                        
                        novelName = meta.attr("content");
                    } catch (Exception e) {
                        Logger.getLogger(GetImgWay.class.getName()).log(Level.SEVERE, "获取小说名错误", e);
                    }

                    return novelName;
                }
            };
    private int value;
    private String name;

    private GetNovelNameWay(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static GetNovelNameWay getIndexWayBayValue(int value) {

        for (GetNovelNameWay way : GetNovelNameWay.values()) {
            if (way.value == value) {
                return way;
            }
        }
        return null;
    }

    public String getNovelName(Document doc, String[] keys) {
        String novelName = null;
        try {
            Element infoEle = doc.getElementById(keys[0]);

            Elements novelNameEles = infoEle.getElementsByTag(keys[1]);

            Element novelNameEle = novelNameEles.first();
            novelName = novelNameEle.text();
        } catch (Exception e) {
            Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取小说名错误", e);
        }

        return novelName;
    }

}
