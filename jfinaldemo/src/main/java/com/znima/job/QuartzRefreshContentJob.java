/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.job;

import com.znima.entity.NovelItem;
import com.znima.entity.RefreshMark;
import com.znima.result.Msg;
import com.znima.service.NovelService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 部分章节会显示自在手打中...，需要重新获取章节内容
 * 
 * @author Administrator
 */
public class QuartzRefreshContentJob implements Job {
    
    Logger logger = LoggerFactory.getLogger(QuartzRefreshContentJob.class);

    private final NovelService novelService = new NovelService();
    
    private static boolean isRunning = false;
    
    /**
     * 是否刷新章节的标记
     */
    private static final Set<String> refreshMarkSet = new HashSet();
    
    private static Long setMarkTime;
    
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
        long currentTimeMillis = System.currentTimeMillis();
        if (setMarkTime == null || setMarkTime + (1000 * 3600) < currentTimeMillis) {
            List<RefreshMark> marks = RefreshMark.dao.getMarks();
            refreshMarkSet.clear();
            for (RefreshMark mark : marks) {
                String markStr = mark.getMark();
                refreshMarkSet.add(markStr);
            }
            setMarkTime = currentTimeMillis;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("QuartzRefreshContentJob:" + sdf.format(new Date()));
        
        List<NovelItem> novels = NovelItem.dao.findSmallItems(500, 10);
        
        logger.info("QuartzRefreshContentJob: novels:" + novels);
        for (NovelItem novelItem : novels) {
            
            String content = novelItem.getContent();
            if (content == null) {
                continue;
            }
            
            boolean hasMark = false;
            
            logger.info("refreshMarkSet->" + refreshMarkSet);
            for (String mark : refreshMarkSet) {
                if (content.contains(mark)) {
                    hasMark = true;
                }
            }
            
            if (hasMark == false) {
                continue;
            }
            
            Msg msg = null;
            try {
                msg = this.novelService.refresh(novelItem.getId());
            } catch (Exception e) {
                logger.info("QuartzRefreshContentJob e:" + e.getMessage());
            }
            logger.info("QuartzRefreshContentJob.execute result:" + msg);
        }
    }
    
}
