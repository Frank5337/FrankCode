package com.zbl.concurrent.wwj.step1.p27;

import java.util.stream.Stream;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 15:01
 * @Description:
 * @Version: $
 */
public class DifferenceWaitAndSleep {

    private static final Object LOCK = new Object();

    //wait 和 sleep的差异
    public static void main(String[] args) {
//        m1();
//        m2();
        Stream.of("T1", "T2", "T3").forEach(name -> {
            new Thread(name) {
                @Override
                public void run() {
//                    m1();
                    m2();
                }
            }.start();
        });
    }

    public static void m1() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread() + "  sleep");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void m2() {
        synchronized (LOCK) {
            try {
                //java.lang.IllegalMonitorStateException
                System.out.println(Thread.currentThread() + "  wait");
                LOCK.wait(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
