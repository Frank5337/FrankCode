package com.zbl.wwj.concurrent.step3.p142_143_Future_Callable;

import java.util.concurrent.*;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/2
 * @Description:
 * @Version: $
 */
public class FutureExample1 {

    public static void main(String[] args) throws Exception {

        // work 1

        // work 2

        //(result from work 1)
        // work 3

//       testGet();
        testGetTimeOut();
    }

    /**
     * {@link Future#get()}
     *
     * @throws ExecutionException
     * @throws InterruptedException who ?
     */
    private static void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //=================================================
        System.out.println("I will be printed quickly.");
        //=================================================
        //main 线程去get  打断只能打断main线程
        Thread caller = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            caller.interrupt();
        }).start();

        Integer result = future.get();
        System.out.println(result);
    }

    /**
     * dist cp
     * <p>
     * yarn applicationJobId
     * <p>
     * process
     * <p>
     * kill -9 process
     * yarn -kill applicationJobId
     * <p>
     * 5 hours  timeOut 任务不会死掉, 会继续执行
     * <p>
     * {@link Future#get(long, TimeUnit)} ()}
     *
     * @throws ExecutionException
     * @throws InterruptedException who ?
     */
    private static void testGetTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("============");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        Integer result = future.get(3, TimeUnit.SECONDS); //java.util.concurrent.TimeoutException
        //这边拿结果timeout了 那边任务还会去执行
        System.out.println(result);
    }

}
