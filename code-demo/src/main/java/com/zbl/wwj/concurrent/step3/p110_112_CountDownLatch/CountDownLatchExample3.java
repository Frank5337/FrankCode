package com.zbl.wwj.concurrent.step3.p110_112_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/10
 * @Description:
 * @Version: $
 */
public class CountDownLatchExample3 {

    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);

        final Thread thread = Thread.currentThread();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("release");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
//                thread.interrupt();
            }
        }).start();

        latch.await(1000, TimeUnit.MILLISECONDS);
        System.out.println("=====");

    }
}
