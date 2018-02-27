/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 小说dto
 *
 * @author Administrator
 */
public class NovelDto {

    private String indexUrl;    //目录URL
    private String novelName; // '小说名',
    private String desc; //'小说描述',
    private String author;//'作者',
    private String image; //'小说封面'图片地址
    private List<NovelItemDto> items; //章节地址s
    
    public NovelDto() {
        this.items = new ArrayList();
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<NovelItemDto> getItems() {
        return items;
    }

    public void setItems(List<NovelItemDto> items) {
        this.items = items;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    @Override
    public String toString() {
        return "NovelDto{" + "novelName=" + novelName + ", \ndesc=" + desc + ", \nauthor=" + author + ", \nimage=" + image + ", \nindexUrl=" + indexUrl
                + ", \nitemUrls=" + items + '}';
    }
}
