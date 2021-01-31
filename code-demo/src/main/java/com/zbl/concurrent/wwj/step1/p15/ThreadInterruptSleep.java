package com.zbl.concurrent.wwj.step1.p15;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/1
 * @Description:
 * @Version: $
 */
public class ThreadInterruptSleep {

    public static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        System.out.println("收到打断信号.");
//                        e.printStackTrace();
//                    }
//                }
                while (true) {
                    synchronized (lock) {
                        try {
                            lock.wait(10);
                        } catch (InterruptedException e) {
                            System.out.println("收到打断信号.");
                            e.printStackTrace();
                            System.out.println(isInterrupted());
                        }
                    }
                    System.out.println("123");
                }
            }
        };

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        //join sleep wait
        t.interrupt();
        System.out.println(t.isInterrupted());

    }

}
