/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.listen;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class H2DBListenerTest {
    
    static Logger logger = LoggerFactory.getLogger(H2DBListenerTest.class);
    
    //H2数据库服务器启动实例
    private static Server tcpServer;
    private static Server webServer;
    
    public H2DBListenerTest() {
        
    }

    
    public static void main(String[] args) throws InterruptedException {
        try {
            //使用org.h2.tools.Server这个类创建一个H2数据库的服务并启动服务，由于没有指定任何参数，那么H2数据库启动时默认占用的端口就是8082
            String[] tctStartArgs = {"-tcp", "-tcpPort", "9092", "-tcpAllowOthers"};
            tcpServer = Server.createTcpServer(tctStartArgs).start();

            String[] webStartArgs = {"-web", "-webPort", "8082", "-webAllowOthers"};
            webServer = Server.createWebServer(webStartArgs).start();
            logger.info("h2数据库启动成功...");
            
            
            Thread.sleep(3500l);
        } catch (SQLException e) {
            logger.error("启动h2数据库出错：" + e.toString(), e);
        }
        
    }
    
}
