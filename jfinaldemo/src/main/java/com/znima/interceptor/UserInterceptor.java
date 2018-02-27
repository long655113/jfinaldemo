/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Administrator
 */
public class UserInterceptor implements Interceptor {
    
    private static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public void intercept(Invocation invctn) {
        logger.info("Before action invoking");
        invctn.invoke();
        logger.info("After action invoking");
    }
}
