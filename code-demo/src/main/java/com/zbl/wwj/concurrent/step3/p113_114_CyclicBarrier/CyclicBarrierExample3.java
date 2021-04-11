package com.zbl.wwj.concurrent.step3.p113_114_CyclicBarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class CyclicBarrierExample3 {

   static class MyCountDownLatch extends CountDownLatch {

       private final Runnable runnable;
       public MyCountDownLatch(int count, Runnable runnable) {
           super(count);
           this.runnable = runnable;
       }

       public void countDown() {
           super.countDown();
           if (getCount() == 0) {
               this.runnable.run();
           }
       }
   }

    public static void main(String[] args) {
        final MyCountDownLatch countDownLatch = new MyCountDownLatch(2, () -> {
            System.out.println("call you");
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " t1, finish.");
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " t2, finish.");
        }).start();

    }
}
