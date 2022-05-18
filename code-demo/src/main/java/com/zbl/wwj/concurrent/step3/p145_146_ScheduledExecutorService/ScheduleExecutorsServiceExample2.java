package com.zbl.wwj.concurrent.step3.p145_146_ScheduledExecutorService;

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
public class ScheduleExecutorsServiceExample2 {

    public static void main(String[] args) throws Exception {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        /**
         * 任务之间的间隔是固定的
         */
        final AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> schedule = executor.scheduleWithFixedDelay(
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
