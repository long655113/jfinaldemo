/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.listen;

import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class H2DBListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(H2DBListener.class);
    
    //H2数据库服务器启动实例
    private Server tcpServer;
    private Server webServer;
    
    /* 
     * Web应用初始化时启动H2数据库
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
            //使用org.h2.tools.Server这个类创建一个H2数据库的服务并启动服务，由于没有指定任何参数，那么H2数据库启动时默认占用的端口就是8082
            String[] tctStartArgs = {"-tcp", "-tcpPort", "9092", "-tcpAllowOthers"};
            tcpServer = Server.createTcpServer(tctStartArgs).start();

            String[] webStartArgs = {"-web", "-webPort", "8082", "-webAllowOthers"};
            webServer = Server.createWebServer(webStartArgs).start();
            logger.info("h2数据库启动成功...");
        } catch (SQLException e) {
            logger.error("启动h2数据库出错：" + e.toString(), e);
        }
    }
    /* 
     * Web应用销毁时停止H2数据库
     */

    public void contextDestroyed(ServletContextEvent sce) {
        if (this.tcpServer != null) {
            // 停止H2数据库
            this.tcpServer.stop();
            this.webServer.stop();
            logger.info("销毁h2数据库...");
            this.tcpServer = null;
            this.webServer = null;
        }
    }
}
