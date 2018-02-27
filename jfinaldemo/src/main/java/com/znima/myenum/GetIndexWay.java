/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.myenum;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 查找目录内容方式
 *
 * @author Administrator
 */
public enum GetIndexWay {

    ID(1, "id") {

                @Override
                public Element getIndexEle(Element e, String[] keys) {
                    if (keys.length == 0) {
                        return null;
                    }
                    Element indexEle = e.getElementById(keys[0]);

                    return indexEle;
                }

            }, CLASS(2, "class") {

                @Override
                public Element getIndexEle(Element e, String[] keys) {
                    if (keys.length == 0) {
                        return null;
                    }
                    
                    Elements indexEles = e.getElementsByClass(keys[0]);
                    if (indexEles == null) {
                        return null;
                    }

                    int order = 0;
                    if (keys.length > 1) {
                        order = Integer.parseInt(keys[1]);
                    }
                    
                    Element index = indexEles.get(order);
                    return index;
                }

            }, OTHER(3, "other") {

                @Override
                public Element getIndexEle(Element e, String[] keys) {
                    return super.getIndexEle(e, keys); //To change body of generated methods, choose Tools | Templates.
                }

            };
    private int value;
    private String name;

    private GetIndexWay(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public static GetIndexWay getIndexWayBayValue(int value) {
        
        for (GetIndexWay way : GetIndexWay.values()) {
            if (way.value == value) {
                return way;
            }
        }
        return null;
    }

    public Element getIndexEle(Element e, String[] keys) {
        return null;
    }

}
