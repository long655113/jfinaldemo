/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.dto;

/**
 *
 * @author Administrator
 */
public class NovelItemDto {

    private String itemName;
    private String url;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NovelItemDto{" + "itemName=" + itemName + ", url=" + url + '}';
    }
}
