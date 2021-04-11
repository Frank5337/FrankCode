package com.zbl.wwj.concurrent.step3.p113_114_CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class CyclicBarrierExample2 {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(() -> {
            try {
                cyclicBarrier.await();
                System.out.println("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(4_000);
                cyclicBarrier.await();
                System.out.println("b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println(cyclicBarrier.getNumberWaiting());//当前等待数
        System.out.println(cyclicBarrier.getParties());//总数 final
        System.out.println(cyclicBarrier.isBroken());//是否被打破
        TimeUnit.SECONDS.sleep(2);
        cyclicBarrier.reset();
        System.out.println("main");
        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());

    }
}
