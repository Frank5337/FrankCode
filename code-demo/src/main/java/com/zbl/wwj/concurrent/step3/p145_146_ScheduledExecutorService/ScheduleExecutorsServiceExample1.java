package com.zbl.wwj.concurrent.step3.p145_146_ScheduledExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/3
 * @Description:
 * @Version: $
 */
public class ScheduleExecutorsServiceExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

//        ScheduledFuture<?> schedule = executor.schedule(() -> System.out.println("I will be invoked!"), 2, TimeUnit.SECONDS);

//        System.out.println(schedule.isDone());
//        System.out.println(schedule.cancel(true));
//        System.out.println(schedule.isDone());

//        ScheduledFuture<Integer> schedule = executor.schedule(() -> 2, 2, TimeUnit.SECONDS);
//
//        System.out.println(schedule.get());

        /**
         * period 2 seconds execute a task
         *
         * but the task spend 5 seconds actually
         *
         * solution 1: crontab/quartz/control-M
         *
         * PERIOD FIRST POLICY 周期至上
         *
         * 0:5
         * 2:5
         * 4:5
         *
         * timer
         * solution 2: (JDK timer)
         *
         * 0:5
         * 5:5
         * 10:5
         * 15:5
         */
//        ScheduledFuture<?> schedule = executor.scheduleAtFixedRate(
//                () -> System.out.println("I am running!   " + System.currentTimeMillis()),
//                2,
//                2,
//                TimeUnit.SECONDS);
//
//        TimeUnit.SECONDS.sleep(10);
//        schedule.cancel(true);

        /**
         * 这种还是类似timer那种方式执行的 第一次的任务没跑完 下一次的任务不会开始
         */
        final AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> schedule = executor.scheduleAtFixedRate(
                () -> {
                    long curr = System.currentTimeMillis();
                    if (interval.get() == 0) {
                        System.out.println("First ");
                    } else {
                        System.out.printf("The actually speed [%d] \n", interval.get());
                    }
                    System.out.println(Thread.currentThread().getName());
                    interval.set(curr);
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                },
                1,
                1,
                TimeUnit.SECONDS);


    }

}
