package com.zbl.wwj.concurrent.step3.p125_126_SeampedLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/17
 * @Description:
 * @Version: $
 */
public class StampedLockExample1 {

    /**
     * ReentrantLock vs synchronized
     * <p>更灵活
     * 声明式的
     * 可以扩展
     * 性能稍好, synchronized有优化 unsafe cas机制
     * </p>
     * ReentrantReadWriteLock
     * <p>
     * R W X
     * W W X
     * W R X
     * R R O 读读不需要锁
     * <p>
     * 100 threads 读写分离
     * 99 threads need read lock
     * 1  threads need write lock
     * 容易引起锁饥饿问题
     *
     * </p>
     *
     * @param args
     */

    private static final StampedLock lock = new StampedLock();

    private static final List<Long> DATA  = new ArrayList<>();

    /**
     * 悲观去读
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable readTask = () -> {
            for (;;) {
                read();
            }
        };

        Runnable writeTask = () -> {
            for (;;) {
                write();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(writeTask);
    }

    private static void read() {
        long stamped = -1;
        try {
            stamped = lock.readLock();
            Optional.of(DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", "")))
                    .ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }

    private static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            System.out.println("write");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }

}
