package com.zbl.wwj.concurrent.step3.p117_119_Semaphore;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class SemaphoreExample4 {


    public static void main(String[] args) throws InterruptedException {

        final MySemaphore semaphore = new MySemaphore(5);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.drainPermits();
                System.out.println("t1 available.");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(5);
            }
            System.out.println("t1 finished.");
        }, "t1");

        TimeUnit.MILLISECONDS.sleep(50);

        Thread t2 = new Thread(() -> {
            boolean b = false;
            try {
//                semaphore.acquire();
//                b = semaphore.tryAcquire();
                b = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                System.out.println(b);
                System.out.println("t2 available.");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (b) {
                    semaphore.release();
                }
            }
            System.out.println("t2 finished.");
        }, "t2");

        t1.start();
        t2.start();

        TimeUnit.MILLISECONDS.sleep(50);

//        while (true) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(semaphore.hasQueuedThreads());
        System.out.println(semaphore.getWaitingThreads());
//            semaphore.tryAcquire()//尝试获取
//        }
    }

    static class MySemaphore extends Semaphore {

        public MySemaphore(int permits) {
            super(permits);
        }

        public MySemaphore(int permits, boolean fair) {
            //尽可能的公平的
            super(permits, fair);
        }

        public Collection<Thread> getWaitingThreads() {
            return super.getQueuedThreads();
        }
    }

}
