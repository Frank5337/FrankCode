package com.zbl.msb.concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: zbl
 * @Date: 20:35 2020/6/6
 * @Description:
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        System.out.println("等人满发车中, ---------");
        CyclicBarrier barrier = new CyclicBarrier(20, () -> {
            System.out.println("够20个人了, 开车");
        });

        for (int i = 0; i <100 ; i++) {
            new Thread( () -> {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
