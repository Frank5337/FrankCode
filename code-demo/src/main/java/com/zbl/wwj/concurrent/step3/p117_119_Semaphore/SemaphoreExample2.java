package com.zbl.wwj.concurrent.step3.p117_119_Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class SemaphoreExample2 {

    /**
     * connection pool
     * when get the available connection/policy
     * 1. Get 1000MS then throw exp
     * 2. blocking
     * 3. discard 丢弃 X
     * 4. Get then throw exp //快速失败
     * 5. Get -> register the callback, -> call you.  回调函数方式
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " in");
                try {
                    semaphore.acquire(2);
                    System.out.println(Thread.currentThread().getName() + " Get the semaphore");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                }
                System.out.println(Thread.currentThread().getName() + " out");

            }).start();
        }

        while (true) {
            //当前可用许可证
            System.out.println("AP->" + semaphore.availablePermits());
            //当前等待队列
            System.out.println("QL->" + semaphore.getQueueLength());
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
