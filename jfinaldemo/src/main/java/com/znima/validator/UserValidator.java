/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 *
 * @author Administrator
 */
public class UserValidator extends Validator {
    //在校验失败时才会调用
    @Override
    protected void handleError(Controller controller) {
        controller.keepPara("user.name");//将提交的值再传回页面以便保持原先输入的值
        controller.render("/add.html");
    }

    @Override
    protected void validate(Controller controller) {
        //验证表单域name，返回信息key,返回信息value
        validateRequiredString("user.name", "nameMsg",
                "请输入名称!");
    }
}
