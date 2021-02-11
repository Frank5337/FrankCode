package com.zbl.concurrent.wwj.step1.p37;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/11
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(4, 8, 12, 300, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        IntStream.range(1, 210)
                .forEach(i -> simpleThreadPool.submit(() -> {
                    System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + "   start");
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + "   finished");
                }));

        Thread.sleep(10_000);
        Thread.sleep(10_000);
        Thread.sleep(10_000);
        System.out.println("shutdown");
        //remove 和 shutdown 阻塞进行
        simpleThreadPool.shutdown();
//        simpleThreadPool.submit(() -> {
//            System.out.println("aaaaaaaaaaaaaaa");
//        });

        //提交返回结果  ?
        //立即shutdown ?
        //shutdown的时候 返回未执行完的任务



    }
}
