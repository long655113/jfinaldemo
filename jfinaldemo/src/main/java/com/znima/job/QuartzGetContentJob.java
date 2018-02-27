/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.job;

import com.znima.entity.NovelItem;
import com.znima.result.Msg;
import com.znima.service.NovelService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class QuartzGetContentJob implements Job {
    
    Logger logger = LoggerFactory.getLogger(QuartzGetContentJob.class);

    private NovelService novelService = new NovelService();
    
    private static boolean isRunning = false;
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        if (isRunning) {
            return;
        }
        
        isRunning = true;
        
        try {
            execute();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        isRunning = false;
        
    }
    
    private void execute() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("executeQuartzGetContentJob:" + sdf.format(new Date()));
        logger.info("executeQuartzGetContentJob:" + sdf.format(new Date()));
        
        List<NovelItem> novels = NovelItem.dao.findUndownloadRandomItems(10);
        
        logger.info("QuartzGetContentJob: novels:" + novels);
        for (NovelItem novelItem : novels) {
            Msg msg = null;
            try {
                msg = this.novelService.refresh(novelItem.getId());
            } catch (Exception e) {
                logger.info("QuartzGetContentJob e:" + e.getMessage());
            }
            logger.info("QuartzGetContentJob.execute result:" + msg);
        }
    }
    
}
