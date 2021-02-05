package com.zbl.concurrent.wwj.step1.p19;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/4 12:38
 * @Description:
 * @Version: $
 */
public class SynchronizedTest {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            synchronized (MONITOR) {
                try {
                    Thread.sleep(200_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
    }
}
