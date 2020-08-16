package com.zbl.test.question;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbl
 * @Date: Created in 18:50 2020/7/29
 * @Description:
 * @Version: $
 */
public class T08_00_Lock_Condition {

    public static void main(String[] args) throws InterruptedException {
        //要求用线程交替打印A1B2C3.....Z26
        char[] aI = "ABCDEFG".toCharArray();
        char[] aC = "1234567".toCharArray();

        Lock lock = new ReentrantLock();
        /**
         * condition的await signal 相当于Object的wait notify
         * 对象监视器, 选择性通知
         * signalAll 对等 notifyAll
         */
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.print(c);
                    condition.signal();//唤醒一个线程
                    condition.await();//等待
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aI) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();


    }
}
