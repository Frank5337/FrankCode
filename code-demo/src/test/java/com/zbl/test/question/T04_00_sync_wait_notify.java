package com.zbl.test.question;

/**
 * @Author: zbl
 * @Date: Created in 18:50 2020/7/29
 * @Description:
 * @Version: $
 */
public class T04_00_sync_wait_notify {

     private static volatile boolean first = false;

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        //要求用线程交替打印A1B2C3.....Z26
        char[] aI = "ABCDEFG".toCharArray();
        char[] aC = "1234567".toCharArray();

        new Thread(() -> {
            synchronized (lock) {
                while (!first) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : aI) {
                    System.out.print(c);
                    try {
                        lock.notify();
                        lock.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                for (char c : aC) {
                    System.out.print(c);
                    first = true;
                    try {
                        lock.notify();
                        lock.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2").start();
    }
}
