package com.zbl.concurrent.msb.aqs;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: zbl
 * @Date: Created in 9:48 2020/6/8
 * @Description: 读写锁
 * @Version: $
 */
public class TestReadWriteLock {

    static Lock lock = new ReentrantLock();

    private static int val;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static Lock readLock  = readWriteLock.readLock();

    static Lock writeLock  = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int value) {
        try {
            lock.lock();
            Thread.sleep(1000);
            val = value;
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void lock() throws Exception{
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        Runnable readA = () -> {
            read(lock);
            countDownLatch.countDown();
        };
        Runnable writeA = () -> {
            write(lock, new Random().nextInt());
            countDownLatch.countDown();
        };
        for (int i = 0; i <18 ; i++) {
            new Thread(readA).start();
        }
        for (int i = 0; i <2 ; i++) {
            new Thread(writeA).start();
        }
        countDownLatch.await();
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void readWriteLock() throws Exception{
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        Runnable readA = () -> {
            read(readLock);
            countDownLatch.countDown();
        };
        Runnable writeA = () -> {
            write(writeLock, new Random().nextInt());
            countDownLatch.countDown();
        };
        for (int i = 0; i <18 ; i++) {
            new Thread(readA).start();
        }
        for (int i = 0; i <2 ; i++) {
            new Thread(writeA).start();
        }
        countDownLatch.await();
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }
}
