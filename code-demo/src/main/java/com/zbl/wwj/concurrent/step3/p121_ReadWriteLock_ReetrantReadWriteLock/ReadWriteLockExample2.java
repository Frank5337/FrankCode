package com.zbl.wwj.concurrent.step3.p121_ReadWriteLock_ReetrantReadWriteLock;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/15 10:13
 * @Description:
 * @Version: $
 */
public class ReadWriteLockExample2 {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    private static final List<Long> data = new ArrayList<>();

    /**
     * W W X
     * W R X
     * R W X
     * R R O
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(ReadWriteLockExample2::write);
        t1.start();
        Thread t2 = new Thread(ReadWriteLockExample2::read);
        t2.start();
        Thread t3 = new Thread(ReadWriteLockExample2::read);
        t3.start();
    }

    @Test
    public void test01() throws Exception {

            System.out.println(File.separator);

    }

    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }


    public static void read() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " read release");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}
