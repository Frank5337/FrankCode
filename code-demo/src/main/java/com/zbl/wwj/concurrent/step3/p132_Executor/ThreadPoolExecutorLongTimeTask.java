package com.zbl.wwj.concurrent.step3.p132_Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/22
 * @Description:
 * @Version: $
 */
public class ThreadPoolExecutorLongTimeTask {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);//守护线程
            return t;
        },
                new ThreadPoolExecutor.AbortPolicy());

        //pall
        IntStream.range(0, 10).boxed().forEach(i -> {
            executorService.execute(() -> {
                while (true) {

                }
            });
        });

        //seq
        executorService.shutdownNow();
        executorService.awaitTermination(5, TimeUnit.SECONDS);//设置成daemon回直接结束
        System.out.println("=========start the sequence working==========");


    }
}
