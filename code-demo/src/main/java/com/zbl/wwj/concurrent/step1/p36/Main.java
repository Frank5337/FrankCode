package com.zbl.wwj.concurrent.step1.p36;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/11
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
//        for (int i = 0; i < 40; i++) {
//            simpleThreadPool.submit(() -> {
//                System.out.println("The runnable " + "be serviced by " + Thread.currentThread().getName() + "   start");
//                try {
//                    Thread.sleep(10_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("The runnable " + "be serviced by " + Thread.currentThread().getName() + "   finished");
//            });
//    }
        IntStream.range(1, 21)
                .forEach(i -> simpleThreadPool.submit(() -> {
                    System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + "   start");
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + "   finished");
                }));


    }
}
