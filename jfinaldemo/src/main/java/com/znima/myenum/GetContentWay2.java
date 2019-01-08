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
 * 获取章节内容策略
 *
 * @author Administrator
 */
public enum GetContentWay2 {

    ID(1, "id"), CLASS(2, "class") {

                @Override
                public String getContent(String url, String key) {
                    String content = null;
                    try {
                        Document root = JsoupUtil.getDoc2(url);

                        //如果访问失败，再试3次
                        if (root == null) {
                            for (int i = 0; i < 3; i++) {
                                root = JsoupUtil.getDoc2(url);
                            }
                        }

                        Elements contentEles = root.getElementsByClass(key);

                        Element contentEle = contentEles.first();

                        content = contentEle.html();
                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "解释章节内容失败:" + url, e);
                    }

                    return content;
                }

            };
    private int value;
    private String name;

    private GetContentWay2(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static GetContentWay2 getIndexWayBayValue(int value) {

        for (GetContentWay2 way : GetContentWay2.values()) {
            if (way.value == value) {
                return way;
            }
        }
        return null;
    }

    public String getContent(String url, String key) {
        String content = null;
        try {
            Document root = JsoupUtil.getDoc2(url);

            //如果访问失败，再试3次
            if (root == null) {
                for (int i = 0; i < 3; i++) {
                    root = JsoupUtil.getDoc2(url);
                }
            }

            Element contentEle = root.getElementById(key);

            content = contentEle.html();
        } catch (Exception e) {
            Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "解释章节内容失败" + url, e);
        }

        return content;
    }

}
