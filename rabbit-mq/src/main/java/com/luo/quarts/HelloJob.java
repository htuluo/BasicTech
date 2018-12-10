package com.luo.quarts;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/12/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail=jobExecutionContext.getJobDetail();
        String name=detail.getJobDataMap().getString("name");
        System.out.println("say hello "+name);
    }
}
