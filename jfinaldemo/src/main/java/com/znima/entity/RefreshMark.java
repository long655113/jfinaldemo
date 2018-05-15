/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.entity;

import com.jfinal.plugin.activerecord.Model;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class RefreshMark extends Model<RefreshMark> {
    private Integer id;
    private String mark;   //刷新标记
    
    public static final RefreshMark dao = new RefreshMark();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    
    public RefreshMark toBean() {
        RefreshMark item = this;
        item.id = item.get("id");
        item.mark = item.get("mark");
        return item;
    }
    
    public List<RefreshMark> getMarks() {
        List<RefreshMark> refreshMark = RefreshMark.dao.find("SELECT id, mark FROM refresh_mark ");
        
        for (RefreshMark item : refreshMark) {
            item.toBean();
        }
        
        return refreshMark;
    }
}
