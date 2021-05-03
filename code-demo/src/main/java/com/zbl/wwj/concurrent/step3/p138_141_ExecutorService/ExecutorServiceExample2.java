package com.zbl.wwj.concurrent.step3.p138_141_ExecutorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/30
 * @Description:
 * @Version: $
 */
public class ExecutorServiceExample2 {

    public static void main(String[] args) throws Exception {
//        testAbortPolicy(); //会抛出异常
//        testDiscardPolicy();//丢了 也不告诉你发生了什么
//        testCallerRunsPolicy();//交给运行者去执行, 谁提交的谁去跑 我不管!
        testDiscardOldestPolicy();//把队列中的任务丢掉而用新放进去的 //the oldest unhandled
    }

    private static void testAbortPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());//拒绝策略

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);

        executorService.submit(() -> System.out.println("x"));


    }

    /**
     * 直接放弃
     *
     * @throws InterruptedException
     */
    private static void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardPolicy());//丢弃策略

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);

        executorService.submit(() -> System.out.println("x"));

        System.out.println("===============================");

    }


    private static void testCallerRunsPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.CallerRunsPolicy());//拒绝策略

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);

        executorService.submit(() -> {
            System.out.println("x");

            System.out.println(Thread.currentThread().getName());
        });

    }

    private static void testDiscardOldestPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardOldestPolicy());//把队列中的任务丢了, 把新放进去的任务放进去了

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("I come from lambda.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);

        executorService.submit(() -> {
            System.out.println("x");

            System.out.println(Thread.currentThread().getName());
        });

    }

}
