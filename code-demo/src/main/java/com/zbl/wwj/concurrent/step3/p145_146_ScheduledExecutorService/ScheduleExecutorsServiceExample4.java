package com.zbl.wwj.concurrent.step3.p145_146_ScheduledExecutorService;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/3
 * @Description:
 * @Version: $
 */
public class ScheduleExecutorsServiceExample4 {

    public static void main(String[] args) throws Exception {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        System.out.println(executor.getExecuteExistingDelayedTasksAfterShutdownPolicy());
//        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
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
                2,
                TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(3);

        executor.shutdown();
        System.out.println("==over==");
    }


    @Test
    public void ScheduledThreadPoolExecutor() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 100; i++) {
            scheduledExecutorService.schedule(new Runnable() {

                @Override
                public void run() {
                    System.out.println("delay 3 seconds");
                }
            }, 3, TimeUnit.SECONDS);
        }

    }

}
