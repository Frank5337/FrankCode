package com.zbl.wwj.concurrent.step3.p120_Lock_ReentrantLock;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/14
 * @Description:
 * @Version: $
 */
public class ReentrantLockExample1 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        IntStream.range(0, 2).forEach(i -> {
//            new Thread(() -> {
//                testUnInterruptibly();
//            }).start();
//        });
        Thread t1 = new Thread(() -> {
            testUnInterruptibly();
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            testUnInterruptibly();
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
//        t2.interrupt();
//        System.out.println("===============");
        Optional.of(lock.getQueueLength()).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThreads()).ifPresent(System.out::println);
        //看当前线程拿没拿锁
        Optional.of(lock.getHoldCount()).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThread(t2)).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThread(t1)).ifPresent(System.out::println);
        Optional.of(lock.isLocked()).ifPresent(System.out::println);
//        Optional.of(lock.get()).ifPresent(System.out::println);

    }

    public static void testTry() {
        boolean result = false;
        try {
            result = lock.tryLock();
            if (!result) {
                System.out.println(Thread.currentThread().getName() + " can not get lock");
                return;
            }
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (result) {
                lock.unlock();
            }
        }
    }

    public static void testTryLock() {
        if (lock.tryLock()) {
            try {
                // manipulate protected state
                System.out.println(Thread.currentThread().getName() + " get the lock");
                for (; ; ) {

                }
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " can not get lock");
            // perform alternative actions
        }
    }

    public static void testInterruptibly() {
        try {
            lock.lockInterruptibly();
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            for (; ; ) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void testUnInterruptibly() {
        try {
            lock.lock();
            Optional.of(lock.getHoldCount()).ifPresent(System.out::println);
            System.out.println(Thread.currentThread().getName() + " getHoldCount " + lock.getHoldCount());
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
//            for (; ; ) {
//
//            }

            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLock() {
        try {
            lock.lock();
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void needLockBySync() {
        synchronized (ReentrantLockExample1.class) {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
