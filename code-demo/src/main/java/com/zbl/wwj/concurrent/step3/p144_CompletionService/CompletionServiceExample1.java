package com.zbl.wwj.concurrent.step3.p144_CompletionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: zbl
 * @Date: Created in 2021/5/3
 * @Description:
 * @Version: $
 */
public class CompletionServiceExample1 {

    public static void main(String[] args) throws Exception {
//        futureDefect2();
        futureDefect3();
    }

    /**
     * no callback
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureDefect1() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        System.out.println("============");
        future.get();
    }

    /**
     * 很鸡肋
     * 不能拿到先执行的那个结果
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void futureDefect2() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    sleep(10);
                    System.out.println("1 finished.");
                    return 1;
                },
                () -> {
                    sleep(20);
                    System.out.println("2 finished.");
                    return 2;
                });
        List<Future<Integer>> futures = service.invokeAll(callables);
        Integer integer1 = futures.get(0).get();
        System.out.println(integer1);

        Integer integer2 = futures.get(1).get();
        System.out.println(integer2);

    }

    /**
     * 很鸡肋
     * 不能拿到先执行的那个结果
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void futureDefect3() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    sleep(10);
                    System.out.println("1 finished.");
                    return 1;
                },
                () -> {
                    sleep(20);
                    System.out.println("2 finished.");
                    return 2;
                });
        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(service.submit(callables.get(0)));
        futures.add(service.submit(callables.get(1)));

        Integer integer1 = futures.get(0).get();
        System.out.println(integer1);

        Integer integer2 = futures.get(1).get();
        System.out.println(integer2);

    }

    /**
     * sleep等待.
     */
    public static void sleep(long s) {
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
