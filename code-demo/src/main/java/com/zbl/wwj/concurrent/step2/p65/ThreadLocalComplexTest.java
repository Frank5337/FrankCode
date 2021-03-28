package com.zbl.wwj.concurrent.step2.p65;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/25
 * @Description:
 * @Version: $
 */
public class ThreadLocalComplexTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private static final Random r = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(r.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
            try {
                Thread.sleep(r.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        t1.join();
        t2.join();
        System.out.println("==============");
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
