/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import cn.dreampie.quartz.QuartzPlugin;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.znima.controller.IndexController;
import com.znima.controller.NovelController;
import com.znima.entity.GetNovelConfig;
import com.znima.entity.Novel;
import com.znima.entity.NovelItem;
import com.znima.entity.RefreshMark;

public class DemoConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);   //默认视图为JSP
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/hello", HelloController.class);
        me.add("/user", UserController.class);
        me.add("/novel", NovelController.class);
    }

    @Override
    public void configPlugin(Plugins me) {
//        C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://120.24.169.189:3306/jfinaldemo?useSSL=false&characterEncoding=utf8",
//                "root", "1qaz2wsx");
//        // 配置mysql驱动
//        cp.setDriverClass("com.mysql.jdbc.Driver");
//        jdbc:h2:tcp://zjl.hmxingkong.com:9092/~/jfinaldemo;MODE=MSSQLServer;DATABASE_TO_UPPER=FALSE
//        C3p0Plugin cp = new C3p0Plugin("jdbc:h2:~/jfinaldemo;MODE=MSSQLServer", "root", "1qaz2wsx");
//        cp.setDriverClass("org.h2.Driver");
        DruidPlugin dp = new DruidPlugin("jdbc:h2:~/jfinaldemo;MODE=MSSQLServer", "root", "1qaz2wsx");
//        DruidPlugin dp = new DruidPlugin("jdbc:h2:tcp://zjl.hmxingkong.com:9092/~/jfinaldemo;MODE=MSSQLServer;DATABASE_TO_UPPER=FALSE", "root", "1qaz2wsx");
        dp.setDriverClass("org.h2.Driver");
        
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        // 配置Mysql方言
        arp.setDialect(new MysqlDialect());
        // 配置属性名(字段名)大小写不敏感容器工厂
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());
//        arp.addMapping("user", "id", User.class);
        arp.addMapping("novel", "id", Novel.class);
        arp.addMapping("novelItem", "id", NovelItem.class);
        arp.addMapping("getNovelConfig", "id", GetNovelConfig.class);
        arp.addMapping("refresh_mark", "id", RefreshMark.class);
        
        //定时任务 quartz
        QuartzPlugin quartz = new QuartzPlugin();
        quartz.setJobs("jobs.properties");
        me.add(quartz);
        
        quartz = new QuartzPlugin();
        quartz.setJobs("getContentJobs.properties");
        me.add(quartz);
        
        quartz = new QuartzPlugin();
        quartz.setJobs("refreshContentJobs.properties");
        me.add(quartz);
    }

    @Override
    public void configInterceptor(Interceptors me) {
    }

    @Override
    public void configHandler(Handlers me) {
    }
}
