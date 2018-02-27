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
 * 查找封面方式
 *
 * @author Administrator
 */
public enum GetAuthorWay {

    ID(1, "id") {

                @Override
                public String getNovelAuthor(Document doc, String[] keys) {
                    String authorName = null;
                    try {
                        Element infoEle = doc.getElementById(keys[0]);

                        Elements novelAuthorEles = infoEle.getElementsByTag(keys[1]);

                        Element novelAuthorEle = novelAuthorEles.first();
                        authorName = novelAuthorEle.text();
                        String[] split = authorName.split("：");
                        if (split.length == 2) {
                            return split[1];
                        }

                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
                    }

                    return authorName;
                }

            }, CLASS(2, "class") {

                @Override
                public String getNovelAuthor(Document doc, String[] keys) {
                    String authorName = null;
                    try {
                        Elements infoEles = doc.getElementsByClass(keys[0]);
                        
                        Element infoEle = infoEles.first();

                        Elements novelAuthorEles = infoEle.getElementsByTag(keys[1]);

                        Element novelAuthorEle = novelAuthorEles.first();
                        authorName = novelAuthorEle.text();
                        String[] split = authorName.split("：");
                        if (split.length == 2) {
                            return split[1];
                        }

                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
                    }

                    return authorName;
                }

            }, OTHER(3, "contain") {

                @Override
                public String getNovelAuthor(Document doc, String[] keys) {
                    String authorName = null;
                    try {
                        Element infoEle = doc.getElementById(keys[0]);

                        Elements novelAuthorEles = infoEle.getElementsContainingText(keys[1]);

                        Element novelAuthorEle = novelAuthorEles.first();
                        authorName = novelAuthorEle.text();
                        String[] split = authorName.split("作者：");
                        if (split.length > 0) {
                            String[] split2 = split[1].split("　");
                            if (split2.length > 0) {
                                return split2[1];
                            }
                            return split[1];
                        }
                        return authorName;
                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
                    }

                    return authorName;
                }

            },meta(4, "meta") {
                
                @Override
                public String getNovelAuthor(Document doc, String[] keys) {
                    String authorName = null;
                    try {
                        
                        Elements metaEles = doc.getElementsByAttributeValue(keys[0], keys[1]);
                        Element metaAuthor = metaEles.first();
                        
                        String author = metaAuthor.attr("content");

                        return author;
                    } catch (Exception e) {
                        Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
                    }

                    return authorName;
                }
            };
    private int value;
    private String name;

    private GetAuthorWay(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public static GetAuthorWay getIndexWayBayValue(int value) {
        
        for (GetAuthorWay way : GetAuthorWay.values()) {
            if (way.value == value) {
                return way;
            }
        }
        return null;
    }

    public String getNovelAuthor(Document doc, String[] keys) {
        String authorName = null;
        try {
            Element infoEle = doc.getElementById(keys[0]);
            
            Elements novelAuthorEles = infoEle.getElementsByTag(keys[1]);
            
            Element novelAuthorEle = novelAuthorEles.first();
            authorName = novelAuthorEle.text();
            String[] split = authorName.split("：");
            if (split.length == 2) {
                return split[1];
            }
            
        } catch (Exception e) {
            Logger.getLogger(JsoupUtil.class.getName()).log(Level.SEVERE, "获取作者错误", e);
        }
        
        return authorName;
    }

}
