package com.zbl.wwj.concurrent.step1.p15;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/1
 * @Description:
 * @Version: $
 */
public class ThreadInterruptJoin {

    public static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1");
            }

        });

        Thread main = Thread.currentThread();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    main.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("interrupt");
            }
        };

        t1.start();
        t2.start();
        try {
            t1.join();//join的是main线程,  要打断main线程才会interrupted
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main");
    }

}
