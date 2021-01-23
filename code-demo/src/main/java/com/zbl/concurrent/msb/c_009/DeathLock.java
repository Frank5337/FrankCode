package com.zbl.concurrent.msb.c_009;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 14:38 2020/3/8
 * @Description: 模拟死锁
 *               共享资源读写的时候, 才需要线程同步
 */
public class DeathLock {

    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    void m1() throws Exception {
        synchronized (DeathLock.o1) {
            TimeUnit.SECONDS.sleep(1000);
            synchronized (DeathLock.o2) {
                System.out.println(111);
            }
        }
    }


    void m2() throws Exception {
        synchronized (DeathLock.o2) {
            TimeUnit.SECONDS.sleep(1000);
            synchronized (DeathLock.o1) {
                System.out.println(222);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                new DeathLock().m1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new DeathLock().m2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
