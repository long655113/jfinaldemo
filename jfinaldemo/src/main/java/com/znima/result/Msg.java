/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.result;

/**
 *
 * @author Administrator
 */
public class Msg {
    private String code;
    private String msg;
    
    public Msg() {
        code = "0001";
        msg = "fail";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" + "code=" + code + ", msg=" + msg + '}';
    }
}
