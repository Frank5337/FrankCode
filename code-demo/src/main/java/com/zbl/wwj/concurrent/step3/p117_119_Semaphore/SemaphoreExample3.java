package com.zbl.wwj.concurrent.step3.p117_119_Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class SemaphoreExample3 {

    /**
     * public void acquire() throws InterruptedException 会被中断
     * public void release() 释放
     *
     * availablePermits() 当前可用凭证
     * getQueueLength()   当前等待队列数量
     *
     *
     * public void acquireUninterruptibly() 不理会中断
     * public void acquireUninterruptibly(int permits)
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
//                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println("t1 finished.");
        });

        TimeUnit.MILLISECONDS.sleep(50);

        Thread t2 = new Thread(() -> {
            try {
//                semaphore.acquire();
                //中断我没用, 不理你的中断信号
                semaphore.acquireUninterruptibly();
//                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println("t2 finished.");
        });

        t1.start();
        t2.start();

        TimeUnit.MILLISECONDS.sleep(50);

        t2.interrupt();

    }

}
