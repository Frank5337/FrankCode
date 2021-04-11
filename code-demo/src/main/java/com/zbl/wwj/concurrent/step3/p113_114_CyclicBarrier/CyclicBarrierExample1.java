package com.zbl.wwj.concurrent.step3.p113_114_CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class CyclicBarrierExample1 {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("call back");
            }
        });

        new Thread(() -> {
            try {
                Thread.sleep(4_000);
                System.out.println("T1 finished.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2_000);
                System.out.println("T2 finished.");
                cyclicBarrier.await();
                System.out.println("The other thread finished too.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

//        cyclicBarrier.await();
//        System.out.println("all of finished.");

        while (true) {
            System.out.println(cyclicBarrier.getNumberWaiting());
            System.out.println(cyclicBarrier.getParties());
            System.out.println(cyclicBarrier.isBroken());
            System.out.println("=========================");
        }

    }
}
