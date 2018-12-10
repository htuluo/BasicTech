package com.luo.quarts;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Collection;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/12/7
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class QuartzStudy {
    public static void main(String[] args) {
        SchedulerFactory schedulerFactory=new StdSchedulerFactory();
        Scheduler scheduler=null;
        try {
            scheduler=schedulerFactory.getScheduler();
            JobDetail job= JobBuilder.newJob(HelloJob.class).withIdentity("JobName","JobGroupName").usingJobData("name","quarts").build();
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1","TriggerGroup1")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()).startNow().build();
            scheduler.scheduleJob(job,trigger);
            scheduler.start();
            Thread.sleep(5000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
