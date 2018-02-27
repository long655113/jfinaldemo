/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.znima.ModelUtil;
import com.znima.entity.User;
import com.znima.interceptor.UserInterceptor;
import com.znima.validator.UserValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class UserController extends Controller {

    @Before(UserInterceptor.class)
    public void index() {
        List<User> list = User.dao.find("select * from user");
       
        setAttr("userList", ModelUtil.modelToMap(list));
        setAttr("test", "hello world");
        render("../index.jsp");
    }

    public void add() {
        render("/add.html");
    }

    public void delete() {
        // 获取表单域名为studentID的值
        // Student.dao.deleteById(getPara("studentID"));
        // 获取url请求中第一个值
        User.dao.deleteById(getParaToInt());
        forwardAction("/user");
    }

    public void update() {
        User student = getModel(User.class);
        student.update();
        forwardAction("/user");
    }

    public void get() {
        Integer id = getParaToInt("id");
//        id = 1;
        User user = User.dao.findById(id);
        setAttr("user", user);
        render("/index2.html");
    }

    @Before(UserValidator.class)
    public void save() {
        User student = getModel(User.class);
//        student.set("id", "mysequence.nextval").save();
        forwardAction("/student");
    }
}
