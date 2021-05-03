package com.zbl.wwj.concurrent.step3.p142_143_Future_Callable;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/2
 * @Description:
 * @Version: $
 */
public class FutureExample2 {

    public static void main(String[] args) throws Exception {

        // work 1

        // work 2

        //(result from work 1)
        // work 3

//        testIsDone();

//        testCancel();

//        testCancelWWJ();

        testCancelWWJ2();
    }

    /**
     * 1. Completion may be due to normal termination,  正常结束
     * 2. an exception, or                              异常
     * 3. cancellation --                               取消
     * true
     * in all of these cases, this method will return
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testIsDone() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        Future<Integer> future = executor.submit(() -> {
            try {
                int i = 1 / 0;
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        System.out.println(future.isDone());

        try {
            Integer integer = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//        System.out.println(future.isDone());//true

//        System.out.println(future.cancel(true));
//        System.out.println(future.isDone());

        //java.util.concurrent.ExecutionException: java.lang.ArithmeticException
        System.out.println(future.isDone());

    }

    /**
     * try to cancel maybe failed
     * <ul>
     *     <li>task is completed already.</li>   任务已经完成了
     *     <li>has already been canceled</li>    已经被取消了
     * </ul>
     *
     * @throws InterruptedException
     */
    private static void testCancel() throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        Future<Integer> future = executor.submit(() -> {
            try {
                System.out.println("future start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("future complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        TimeUnit.SECONDS.sleep(2);
        System.out.println(future.isCancelled());
        //if the thread executing this
        //     * task should be interrupted; otherwise, in-progress tasks are allowed
        //     * to complete
//        future.cancel(false);//不中断
        future.cancel(true);//中断
        System.out.println(future.isCancelled());
    }

    /**
     * try to cancel maybe failed
     * <ul>
     *     <li>task is completed already.</li>   任务已经完成了
     *     <li>has already been canceled</li>    已经被取消了
     * </ul>
     *
     * @throws InterruptedException
     */
    private static void testCancelWWJ() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        Future<Integer> future = executor.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            while (atomicBoolean.get()) {
            }
            return 10;
        });

//        System.out.println(future.get());
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        //任务没跑完, 没中断
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());


    }

    /**
     * try to cancel maybe failed
     * <ul>
     *     <li>task is completed already.</li>   任务已经完成了
     *     <li>has already been canceled</li>    已经被取消了
     * </ul>
     *
     * @throws InterruptedException
     */
    private static void testCancelWWJ2() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5
//                ,
//                new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        Thread t = new Thread();
//                        t.setDaemon(true);
//                        return t;
//                    }
//                }
        );

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        Future<Integer> future = executor.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            while (atomicBoolean.get()) {
//            }
            while (!Thread.interrupted()) {
                //aaa
            }
            System.out.println("111111111111");
            return 10;
        });

//        System.out.println(future.get());
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        //任务没跑完, 没中断
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        System.out.println(future.get());//java.util.concurrent.CancellationException


    }

}
