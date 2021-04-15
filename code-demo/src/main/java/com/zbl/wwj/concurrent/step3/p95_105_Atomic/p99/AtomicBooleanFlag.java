package com.zbl.wwj.concurrent.step3.p95_105_Atomic.p99;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/7 15:10
 * @Description:
 * @Version: $
 */
public class AtomicBooleanFlag {

    private static final AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag.get()) {
                try {
                    Thread.sleep(1000);
                    System.out.println("I'm working.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("I am finished.");
        }).start();

        Thread.sleep(5000);

        new Thread(() -> {
            flag.set(false);
        }).start();

//        flag.set(false);
    }


}
