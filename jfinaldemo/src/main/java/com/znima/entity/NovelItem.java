/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.entity;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.znima.util.FileUtil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class NovelItem extends Model<NovelItem> {

    private Integer id;
    private String url;   //章节地址
    private Integer nextId;
    private Integer preId;    //上一章
    private Integer norvelId; //小说ID
    private String title; //章节标题
    private String content;   //章节内容
    private String contentFile; //章节内容文件
    private String remark;    //备注
    private Date createTime;  //创建时间
    private Integer file_length;    //章节内容长度

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public Integer getNorvelId() {
        return norvelId;
    }

    public void setNorvelId(Integer norvelId) {
        this.norvelId = norvelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentFile() {
        return contentFile;
    }

    public void setContentFile(String contentFile) {
        this.contentFile = contentFile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFile_length() {
        return file_length;
    }

    public void setFile_length(Integer file_length) {
        this.file_length = file_length;
    }

    public static final NovelItem dao = new NovelItem();

    public NovelItem toBean() {
        NovelItem item = this;

        item.id = item.get("id");
        item.url = item.get("url");   //章节地址
        item.nextId = item.get("nextId");
        item.preId = item.get("preId");    //上一章
        item.norvelId = item.get("norvelId"); //小说ID
        item.title = item.get("title"); //章节标题
//        item.content = item.get("content");   //章节内容
        item.remark = item.get("remark");    //备注
        item.createTime = item.get("createTime");  //创建时间
        
        item.file_length = item.get("FILE_LENGTH"); //章节内容
        
        item.contentFile = item.get("contentFile"); //章节内容文件
        if (item.contentFile != null && !item.contentFile.trim().equals("")) {
            String fileContent = FileUtil.getFileContent(item.contentFile);
            item.content = fileContent;
            item.put("content", fileContent);
        }

        return item;
    }

    public boolean deleteByNovelId(Integer novelId) {
        int update = Db.update("DELETE FROM novelItem WHERE norvelId=?", novelId);
        return update > 0;
    }

    /**
     * 获取未下载章节，每部小说只取一条未下载章节
     *
     * @return
     */
    public List<NovelItem> findUndownloadItems() {
        List<NovelItem> novels = dao.find("SELECT t1.norvelId, t1.createTime,min(t1.id) id\n"
                + "FROM novelItem t1,\n"
                + "(\n"
                + "SELECT norvelId,MIN(createTime) createTime\n"
                + "FROM \n"
                + "(\n"
                + "SELECT *\n"
                + "FROM novelItem t1\n"
                + "where contentFile IS NULL\n"
                + ") t\n"
                + "GROUP BY norvelId\n"
                + ") t2\n"
                + "WHERE t1.norvelId = t2.norvelId AND t1.createTime=t2.createTime  AND t1.contentFile IS NULL \n"
                + "GROUP BY t1.norvelId, t1.createTime");

        for (NovelItem item : novels) {
            item.toBean();
        }

        return novels;
    }

    /**
     * 随机获取未下载章节获取未下载章节，每部小说只取一条未下载章节
     *
     * @return
     */
    public List<NovelItem> findUndownloadRandomItems(int limit) {
        List<NovelItem> novels = dao.find("SELECT norvelId, createTime, id \n"
                + "FROM NOVELITEM  \n" +
                "where CONTENTFILE  is null \n" +
                "order by random() \n" +
                "limit " + limit);

        for (NovelItem item : novels) {
            item.toBean();
        }

        return novels;
    }
    
    /**
     * 随机获取两天内小章节
     * @param smallFileLength
     * @param limit
     * @return 
     */
    public List<NovelItem> findSmallItems(int smallFileLength, int limit) {
        List<NovelItem> novels = dao.find("SELECT norvelId, createTime, id, contentFile \n"
                + "FROM NOVELITEM  \n" +
                "where DAY_OF_YEAR(now()) - DAY_OF_YEAR(createTime) < 2 and FILE_LENGTH < " + smallFileLength + 
                " order by random() \n" +
                " limit " + limit);

        for (NovelItem item : novels) {
            item.toBean();
        }

        return novels;
    }

    @Override
    public String toString() {
        return "NovelItem{" + "id=" + id + ", url=" + url + ", nextId=" + nextId + ", preId=" + preId + ", norvelId=" + norvelId + ", title=" + title + ", content=" + content + ", remark=" + remark + ", createTime=" + createTime + '}';
    }
}
