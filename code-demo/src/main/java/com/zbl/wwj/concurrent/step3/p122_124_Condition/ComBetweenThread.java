package com.zbl.wwj.concurrent.step3.p122_124_Condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/15
 * @Description:
 * @Version: $
 */
public class ComBetweenThread {

    private static volatile int data = 0;

    private static volatile boolean noUse = false;

    private static final Object MONITOR = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                buildData();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                useData();
            }
        }).start();

    }

    private static void buildData() {
        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                data++;
                TimeUnit.SECONDS.sleep(1);
                Optional.of(Thread.currentThread().getName() + " P: " + data).ifPresent(System.out::println);
                noUse = true;
                MONITOR.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private static void useData() {
        synchronized (MONITOR) {
            while (!noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
                Optional.of(Thread.currentThread().getName() + " C: " + data).ifPresent(System.out::println);
                noUse = false;
                MONITOR.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
