/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.controller;

import com.jfinal.core.Controller;

/**
 *
 * @author Administrator
 */
public class IndexController extends Controller {

    public void index() {
        forwardAction("/novel/novelList");
    }
}
