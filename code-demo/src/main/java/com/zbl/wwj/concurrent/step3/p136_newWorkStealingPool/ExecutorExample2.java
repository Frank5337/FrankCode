package com.zbl.wwj.concurrent.step3.p136_newWorkStealingPool;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/24
 * @Description:
 * @Version: $
 */
public class ExecutorExample2 {

    public static void main(String[] args) throws Exception {
        Optional.of(Runtime.getRuntime().availableProcessors())
                .ifPresent(System.out::println);
        /**
         * Creates a work-stealing thread pool using all
         *
         * new ForkJoinPool
         *       (Runtime.getRuntime().availableProcessors(), 获取CPU的核数
         *       ForkJoinPool.defaultForkJoinWorkerThreadFactory,
         *       null, true);
         *
         * ForkJoinPool
         *     public void execute(Runnable task) {
         *         if (task == null)
         *             throw new NullPointerException();
         *         ForkJoinTask<?> job;
         *         if (task instanceof ForkJoinTask<?>) // avoid re-wrap
         *             job = (ForkJoinTask<?>) task;
         *         else
         *             job = new ForkJoinTask.RunnableExecuteAction(task);
         *         externalPush(job);
         *     }
         *
         */
        ExecutorService service = Executors.newWorkStealingPool();

        List<Callable<String>> collect = IntStream.range(0, 20).boxed().map(i -> {
            return (Callable<String>) () -> {
                System.out.println(Thread.currentThread().getName());
                slSeconds(2);
                return "Task-" + i;
            };
        }).collect(Collectors.toList());

        //cpu核数12个 所以有12个线程
        service.invokeAll(collect).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);

    }

    private static void slSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
