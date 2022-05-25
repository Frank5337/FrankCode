package com.zbl.zcy.leetcode;

import java.util.concurrent.Semaphore;

/**
 * @Author: zbl
 * @Date: Created in 2022/5/25
 * @Description:
 * @Version: $
 */
public class leetcode1117 {

    public static class H2O {

        public H2O() {

        }

        Semaphore h = new Semaphore(2);
        Semaphore o = new Semaphore(2);

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            h.acquire(1);
            releaseHydrogen.run();
            o.release(1);
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            o.acquire(2);
            releaseOxygen.run();
            h.release(2);
        }
    }

    static Semaphore h = new Semaphore(2);
    static Semaphore o = new Semaphore(2);

    public static void main(String[] args) {
         new Thread(() -> {
             while (true) {
                 try {
                     h.acquire(1);
                     System.out.println("h");
                     o.release(1);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
        new Thread(() -> {
            while (true) {
                try {
                    o.acquire(2);
                    System.out.println("o");
                    h.release(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
