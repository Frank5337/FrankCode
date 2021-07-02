package com.zbl.wwj.concurrent.step3.p137_Scheduler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/25 10:17
 * @Description:
 * @Version: $
 */
public class TimerScheduler {

    /**
     * scheduler solution
     * Timer/TimerTask
     * SchedulerExecutorService
     * <p>
     * crontab
     * corn4j
     * quartz
     *
     * Timer:
     *  Question
     *      when the timerTask process more than 1 seconds what happen?
     *
     *      //超时了, 会卡住, 等他结束才会跑
     *
     * <p>
     *     crontab 不会卡住
     * </p>
     *
     * interval correct
     *
     * Control-M
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        final TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("=========" + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.schedule(task, 1000, 1000);
    }

}
