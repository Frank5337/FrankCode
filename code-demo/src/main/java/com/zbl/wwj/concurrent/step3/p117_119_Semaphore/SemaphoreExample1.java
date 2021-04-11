package com.zbl.wwj.concurrent.step3.p117_119_Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class SemaphoreExample1 {

    public static void main(String[] args) {
        final SemaphoreLock semaphoreLock = new SemaphoreLock();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    semaphoreLock.lock();
                    System.out.println(Thread.currentThread().getName() + " get the SemaphoreLock");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphoreLock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " release lock");
            }).start();
        }

    }

    static class SemaphoreLock {

        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }

    private static synchronized void m() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
