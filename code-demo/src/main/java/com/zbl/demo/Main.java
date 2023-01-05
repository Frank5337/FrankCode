package com.zbl.demo;

import com.zbl.wwj.concurrent.step2.p71.CountDown;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2022/7/21
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main1(String[] args) throws InterruptedException {
        long st = System.currentTimeMillis();
        CountDown countDown = new CountDown(3);
        Runnable r = () -> {
            long start = System.currentTimeMillis();
            long res = 0;
            for (int i = 0; i < 98989898; i++) {
                res += i;
            }
            long end = System.currentTimeMillis();
            System.out.printf("Thread:[%s], used:[%s]ms, result:[%s]%n", Thread.currentThread().getName(), (end - start), res);
            countDown.down();
        };
        for (int i = 0; i < 3; i++) {
            new Thread(r).start();
        }
        countDown.await();
        long ed = System.currentTimeMillis();
        System.out.printf("Thread:[%s], total used:[%s]ms", Thread.currentThread().getName(), (ed - st));
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 3,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(3));
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(finalI * 200000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            System.out.println(threadPool.getPoolSize() + ":" + i);
        }

    }

}
