package com.zbl.concurrent.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 17:24 2020/6/14
 * @Description:
 */
public class TestCountDownLatch {

    volatile List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        TestCountDownLatch t = new TestCountDownLatch();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                if (t.size() != 5) {
                    latch.await();
                    System.out.println("t2 结束");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add " + i);
                if (t.size() == 5) {
                    latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1").start();


    }
}
