/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.entity;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.znima.ModelUtil;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Novel extends Model<Novel> {
    
    private Integer id;
    private String novelName;
    private String desc;
    private String author;
    private String image;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public static final Novel dao = new Novel();
    
    public Novel toBean() {
        Novel item = this;
        item.id = item.get("id");
        item.novelName = item.get("novelName");
        item.desc = item.get("desc");
        item.author = item.get("author");
        item.image = item.get("image");
        item.createTime = item.get("createTime");
        return item;
    }
    
    public List<Novel> getNovels() {
        List<Novel> novels = Novel.dao.find("SELECT id,novelName,`desc`,author,image,createTime FROM `novel` ");
        for (Novel item : novels) {
            item.toBean();
        }
        
        return novels;
    }
    
    /**
     * 查询章节列表，不带章节内容
     * @return 
     */
    public List<NovelItem> getNovelItems() {
        List<NovelItem> list = NovelItem.dao.find("SELECT id,url,title, createTime, file_length FROM `novelItem` WHERE novelId=" + get("id") + " order by id");
        
        for (NovelItem item : list) {
            item.toBean();
        }
        
        return list;
    }
    
    /**
     * 查询章节列表，不带章节内容
     * @param page
     * @param sortType
     * @return 
     */
    public Page<NovelItem> getNovelItemsPage(Integer page, String sortType) {
        if (page == null) {
            page = 1;
        }
        
        return NovelItem.dao.paginate(page, 100, "SELECT id,url,title, createTime ", "FROM `novelItem` WHERE novelId=" + get("id") + " order by id " + sortType);
    }
    
    /**
     * 查询章节列表，包含章节内容
     * @return 
     */
    public List<NovelItem> getNovelItemsWithContent() {
        List<NovelItem> list = NovelItem.dao.find("SELECT id,url,title, contentFile, createTime FROM `novelItem` WHERE novelId=" + get("id") + " order by id");
        
        for (NovelItem item : list) {
            item.toBean();
        }
        
        return list;
    }
    
    public NovelItem getLastestItem() {
        NovelItem item = NovelItem.dao.findFirst("SELECT id,url,title, contentFile, createTime FROM `novelItem` WHERE novelId=" + get("id") + " order by id desc limit 0,1");
        
        return item;
    }
    
    public List<Map<String, Object>> getUndownloadNovelItems() {
        List<NovelItem> list = NovelItem.dao.find("SELECT id,url,title,createTime FROM `novelItem` WHERE contentFile is null and novelId=" + get("id") + " order by id");
        
        for (NovelItem item : list) {
            item.toBean();
        }
        
        return ModelUtil.modelToMap(list);
    }
    
    public boolean deleteByNovelId(Integer novelId) {
        boolean result = Novel.dao.deleteById(novelId);
        return result;
    }

    @Override
    public String toString() {
        return "Novel{" + "id=" + id + ", novelName=" + novelName + ", desc=" + desc + ", author=" + author + ", image=" + image + ", createTime=" + createTime + '}';
    }
}
