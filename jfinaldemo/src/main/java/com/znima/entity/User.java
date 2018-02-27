/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.entity;

import com.jfinal.plugin.activerecord.Model;

/**
 *
 * @author Administrator
 */
public class User extends Model<User> {
    public static final User dao = new User();
    
    public User getUser() {
        return User.dao.findById(get("id"));
    }
}
