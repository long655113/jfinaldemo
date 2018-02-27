/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfinal.core.JFinal;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author Administrator
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        WebAppContext context = new WebAppContext();
        JFinal.start("src/main/webapp", 8080, "/", 5);
        
//        Server server = new Server(8080);  
//  
//        WebAppContext context = new WebAppContext();  
//        context.setContextPath("/myapp");  
//        context.setWar("C:\\f\\freeSvn\\long655126\\jfinal\\jfinaldemo\\target/jfinaldemo-1.0-SNAPSHOT.war");  
//        server.setHandler(context);  
//  
//        server.start();  
//        server.join(); 
    }
    
}
