package com.zbl.wwj.concurrent.step3.p132_Executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/22
 * @Description:
 * @Version: $
 */
public class ThreadPoolExecutorTask {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), (ThreadFactory) Thread::new,
                new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 20).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(Thread.currentThread().getName() + "[ " + i + "] finish done.");
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            });
        });

//        executorService.shutdown();//并没有阻塞, 等线程结束才会销毁
        List<Runnable> runnableList = null;//并没有阻塞, 返回队列中的Runnable 等执行中的runnable结束后销毁
        try {
            runnableList = executorService.shutdownNow();
            System.out.println("==============over==============");
            System.out.println(runnableList);
        } catch (Exception e) {
//            e.printStackTrace();
        }

//        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("==============over==============");
        System.out.println(runnableList.size() );
        //other work.

        /**
         * 20 threads
         *    10 threads work
         *    10 idle 空闲
         *
         * shutdown invoked
         *
         *    10 waiting to finished the work
         *    10 interruped.
         *    20 idle threads will exit
         *
         */

        /**
         * shutdownNow
         *
         * 10 threads queues elements 10
         * 10 running
         * 10 stored in the blocking queue
         *
         * 1. return List<Runnable> 10个没处理完 remain 10 un handle runnable.排干
         * 2. still work for the runnable by core thread //打断所有的
         *
         * 空闲的打断
         */
    }
}
