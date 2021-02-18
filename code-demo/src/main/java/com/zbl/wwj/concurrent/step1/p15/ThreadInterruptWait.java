package com.zbl.wwj.concurrent.step1.p15;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/1
 * @Description:
 * @Version: $
 */
public class ThreadInterruptWait {

    public static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        lock.wait(10);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号.");
                        e.printStackTrace();
                        System.out.println(Thread.interrupted());
                    }
                }
                System.out.println("123");
            }

        });

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        //join sleep wait
        t.interrupt();
        System.out.println(t.isInterrupted());

    }

}
