package com.zbl.wwj.concurrent.step3.p138_141_ExecutorService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/30
 * @Description:
 * @Version: $
 */
public class ExecutorServiceExample4 {

    public static void main(String[] args) throws Exception {
//        testInvokeAny();
//        testInvokeAnyTimeOut();
//        testInvokeAll();
//        testInvokeAllTimeOut();
//        testSubmitRunnable();
        testSubmitRunnableWithResult();
    }

    /**
     * Question:
     * when the result returned, other callable will be keep on process?
     * <p>
     * Answer:
     * Other callable will be canceled. (if other not process finished)
     * Note:
     * The invokeAny will be blocked caller
     * <p>
     * {@link ExecutorService#invokeAny(Collection)}
     */
    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = IntStream.range(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    System.out.println(Thread.currentThread().getName() + "=" + i);
                    return i;
                }
        ).collect(Collectors.toList());
        //返回随机一个结果 那个完成返回哪个
        Integer integer = executorService.invokeAny(callables);
        System.out.println("==========finished=========");
        System.out.println(integer);
    }

    /**
     * <p>
     * {@link ExecutorService#invokeAny(Collection, long, TimeUnit)} (Collection)}
     */
    private static void testInvokeAnyTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Integer integer = executorService.invokeAny(
                IntStream.range(0, 5).boxed().map(
                        i -> (Callable<Integer>) () -> {
                            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                            System.out.println(Thread.currentThread().getName() + "=" + i);
                            return i;
                        }
                ).collect(Collectors.toList()), 7, TimeUnit.SECONDS);
        System.out.println("==========finished=========");
        System.out.println(integer);
    }

    /**
     * <p>
     * {@link ExecutorService#invokeAll(Collection)} (Collection)} (Collection)}
     * <p>
     * RxJava Reacvite Java
     */
    private static void testInvokeAll() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //第一阶段执行完才会第二阶段 然后第三阶段
        executorService.invokeAll(
                IntStream.range(0, 5).boxed().map(
                        i -> (Callable<Integer>) () -> {
//                            TimeUnit.SECONDS.sleep(5);
                            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                            System.out.println(Thread.currentThread().getName() + "=" + i);
                            return i;
                        }
                ).collect(Collectors.toList()))
//                .().map(future -> {
                .parallelStream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }).forEach(System.out::println);
        System.out.println("==========finished=========");
    }

    /**
     * <p>
     * {@link ExecutorService#invokeAll(Collection, long, TimeUnit)}
     *
     * @throws TimeoutException 超时
     */
    private static void testInvokeAllTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //第一阶段执行完才会第二阶段 然后第三阶段
        executorService.invokeAll(
                IntStream.range(0, 5).boxed().map(
                        i -> (Callable<Integer>) () -> {
//                            TimeUnit.SECONDS.sleep(5);
                            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                            System.out.println(Thread.currentThread().getName() + "=" + i);
                            return i;
                        }
                ).collect(Collectors.toList()), 3, TimeUnit.SECONDS)
//                .().map(future -> {
                .parallelStream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }).forEach(System.out::println);
        System.out.println("==========finished=========");
    }

    /**
     * {@link ExecutorService#submit(Runnable)}
     *
     */
    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> submit = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Object o = submit.get();
        //这个result没有意义
        System.out.println("R: " + o);
    }

    /**
     * {@link ExecutorService#submit(Runnable, Object)} (Runnable)}
     */
    private static void testSubmitRunnableWithResult() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = "DONE";
        Future<?> submit = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);
        Object o = submit.get();
        //这个result就是传进去的result
        System.out.println("R: " + o);
    }




}
