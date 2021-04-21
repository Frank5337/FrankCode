package com.zbl.wwj.concurrent.step3.p121_ReadWriteLock_ReetrantReadWriteLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/15 10:13
 * @Description:
 * @Version: $
 */
public class ReadWriteLockExample1 {

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final List<Long> data = new ArrayList<>();

    /**
     * W W X
     * W R X
     * R W X
     * R R O
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(ReadWriteLockExample1::write);
        t1.start();
        Thread t2 = new Thread(ReadWriteLockExample1::read);
        t2.start();
    }

    public static void write() {
        try {
            lock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void read() {
        try {
            lock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " read release");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
