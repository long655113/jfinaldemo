/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.entity;

import com.jfinal.plugin.activerecord.Model;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GetNovelConfig extends Model<GetNovelConfig> {

    private Integer id;
    private String webSiteName;   //网站名称
    private String url;   //网站地址
    private Integer getIndexWayValue; //获取目录方式1:id2:class3:其它
    private String key;   //目录查找标志
    private Integer getImgWayValue;   //'获取封面图片方式1：id2：class3:其它
    private String imgKey;    //封面图片地址
    private Integer getNovelNameWayValue; //获取小说名称方式1:id2:class3:other
    private String novelNameKeys; //获取小说名称keyS
    private Integer getAuthorWayValue;    //获取作者策略
    private String novelAuthorKeys;   //作者标志s
    private Integer getDescWayValue;  //获取简介策略
    private String novelDescKeys; //简介标志s
    private Integer descKey2Index;    //简介所在节点名的序号,从0开始
    private Integer getContentWayValue;   //获取章节内容策略1:id2:class
    private String itemKey;   //章节标志
    private Date createTime;
    private String nextPMark;   //章节分页显示时，是否有下一页的标志
    private String nextPSelect; // cssSelect 下一面a标签的获取值
    
    public GetNovelConfig toBean() {
        GetNovelConfig item = this;
        
        item.id = item.get("id");
        item.webSiteName = item.get("webSiteName");
        item.url = item.get("url");
        item.getIndexWayValue = item.get("getIndexWayValue");
        item.key = item.get("key");
        item.getImgWayValue = item.get("getImgWayValue");
        item.imgKey = item.get("imgKey");
        item.getNovelNameWayValue = item.get("getNovelNameWayValue");
        item.novelNameKeys = item.get("novelNameKeys");
        item.getAuthorWayValue = item.get("getAuthorWayValue");
        item.novelAuthorKeys = item.get("novelAuthorKeys");
        item.getDescWayValue = item.get("getDescWayValue");
        item.novelDescKeys = item.get("novelDescKeys");
        item.descKey2Index = item.get("descKey2Index");
        item.getContentWayValue = item.get("getContentWayValue");
        item.itemKey = item.get("itemKey");
        item.createTime = item.get("createTime");
        item.itemKey = item.get("nextPMark");
        item.itemKey = item.get("nextPSelect");
        
        return item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebSiteName() {
        return webSiteName;
    }

    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGetIndexWayValue() {
        return getIndexWayValue;
    }

    public void setGetIndexWayValue(Integer getIndexWayValue) {
        this.getIndexWayValue = getIndexWayValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getGetImgWayValue() {
        return getImgWayValue;
    }

    public void setGetImgWayValue(Integer getImgWayValue) {
        this.getImgWayValue = getImgWayValue;
    }

    public String getImgKey() {
        return imgKey;
    }

    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }

    public Integer getGetNovelNameWayValue() {
        return getNovelNameWayValue;
    }

    public void setGetNovelNameWayValue(Integer getNovelNameWayValue) {
        this.getNovelNameWayValue = getNovelNameWayValue;
    }

    public String getNovelNameKeys() {
        return novelNameKeys;
    }

    public void setNovelNameKeys(String novelNameKeys) {
        this.novelNameKeys = novelNameKeys;
    }

    public Integer getGetAuthorWayValue() {
        return getAuthorWayValue;
    }

    public void setGetAuthorWayValue(Integer getAuthorWayValue) {
        this.getAuthorWayValue = getAuthorWayValue;
    }

    public String getNovelAuthorKeys() {
        return novelAuthorKeys;
    }

    public void setNovelAuthorKeys(String novelAuthorKeys) {
        this.novelAuthorKeys = novelAuthorKeys;
    }

    public Integer getGetDescWayValue() {
        return getDescWayValue;
    }

    public void setGetDescWayValue(Integer getDescWayValue) {
        this.getDescWayValue = getDescWayValue;
    }

    public String getNovelDescKeys() {
        return novelDescKeys;
    }

    public void setNovelDescKeys(String novelDescKeys) {
        this.novelDescKeys = novelDescKeys;
    }

    public Integer getDescKey2Index() {
        return descKey2Index;
    }

    public void setDescKey2Index(Integer descKey2Index) {
        this.descKey2Index = descKey2Index;
    }

    public Integer getGetContentWayValue() {
        return getContentWayValue;
    }

    public void setGetContentWayValue(Integer getContentWayValue) {
        this.getContentWayValue = getContentWayValue;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextPMark() {
        return nextPMark;
    }

    public void setNextPMark(String nextPMark) {
        this.nextPMark = nextPMark;
    }

    public String getNextPSelect() {
        return nextPSelect;
    }

    public void setNextPSelect(String nextPSelect) {
        this.nextPSelect = nextPSelect;
    }

    public static final GetNovelConfig dao = new GetNovelConfig();

    public GetNovelConfig findByUrl(String url) {
        GetNovelConfig config = dao.findFirst("SELECT * FROM getNovelConfig WHERE url=?", url);
        if (config == null) {
            return null;
        }
        
        config = config.toBean();
        return config;
    }
    
    public List<GetNovelConfig> findNovelConfigs() {
        List<GetNovelConfig> configs = GetNovelConfig.dao.find("SELECT * FROM `getNovelConfig`");
        
        for (GetNovelConfig item : configs) {
            item.toBean();
        }
        
        return configs;
    }
}
