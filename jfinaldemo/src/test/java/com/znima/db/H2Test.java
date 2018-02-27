/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.db;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class H2Test {
    
    Logger logger = LoggerFactory.getLogger(H2Test.class);

    @Test
    public void h2Test() throws SQLException, InterruptedException {
//        Server server = Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers").start();
        Server server = Server.createWebServer("-trace");
        boolean running = server.isRunning(true);
        logger.info("" + running);
        
        
//        Thread.sleep(400000000);
    }
}
