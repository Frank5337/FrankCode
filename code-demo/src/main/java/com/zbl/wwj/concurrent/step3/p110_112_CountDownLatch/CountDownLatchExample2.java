package com.zbl.wwj.concurrent.step3.p110_112_CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/10
 * @Description:
 * @Version: $
 */
public class CountDownLatchExample2 {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("Do some initial working.");
            try {
                Thread.sleep(1000);
                latch.await();
                System.out.println("Do other working...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("release");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();

        new Thread(() -> {
            System.out.println("any prepare for some data.");
            try {
                Thread.sleep(1000);
                System.out.println("Data prepare for done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();
    }
}
