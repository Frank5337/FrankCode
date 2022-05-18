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
public class ScheduleExecutorsServiceExample3 {

    public static void main(String[] args) throws Exception {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        System.out.println(executor.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        //true 之后, 任务依然会定期的执行
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
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
                2,
                TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(1);

        executor.shutdown();
        System.out.println("==over==");
    }

}
