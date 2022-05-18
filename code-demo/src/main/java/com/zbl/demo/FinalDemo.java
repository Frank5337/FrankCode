package com.zbl.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbl
 * @Date: 1:13 2020/3/8
 * @Description:
 */
public class FinalDemo {

    public static void main(String[] args) {

        Lock reentrantLock = new ReentrantLock();
        new Thread(() -> {
            try {
                int i = 0;
                while (i++ < 3) {
                    reentrantLock.lock();
                    // Node a -> b -> c
                    // Node h = a
                    // a = null a.next ?  xxx
                    // Node h = a.next b -> c
                    Thread.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                int i = 0;
                while (i++ < 3) {
                    reentrantLock.lock();
                    // Node a -> b -> c
                    // Node h = a
                    // a = null a.next ?  xxx
                    // Node h = a.next b -> c
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();

            }
        }, "t2").start();

        //        m1(10);
    }

    public static void m1(final Integer integer) {
//        integer = 1;
        System.out.println(integer);
    }
}
