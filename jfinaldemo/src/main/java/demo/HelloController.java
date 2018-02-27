/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfinal.core.Controller;

public class HelloController extends Controller {

    public void index() {
        renderText("Hello JFinal World.");
    }
}
