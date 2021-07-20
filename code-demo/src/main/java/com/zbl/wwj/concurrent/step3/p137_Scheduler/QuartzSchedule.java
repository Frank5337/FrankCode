package com.zbl.wwj.concurrent.step3.p137_Scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/25 10:43
 * @Description:
 * @Version: $
 */
public class QuartzSchedule {

    /**
     * 类似与 crontab 任务执行时长不影响新任务
     * @param args
     * @throws SchedulerException
     */
    public static void main(String[] args) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("Job1", "Group1").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }


}
