package com.zbl.wwj.concurrent.step3.p135_newFixed_newCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/24
 * @Description:
 * @Version: $
 */
public class ExecutorExample1 {

    public static void main(String[] args) throws Exception {
//        useCachedThreadPool();
//        useFixedSizePool();
        useSinglePool();
    }

    /**
     * newSingleThreadExecutor difference between one Thread
     * <p>
     * Thread will die after finished work, but SingleThreadExecutor can always alive.
     * Thread can not put the submitted runnable to the cache queue bug singleThreadPool can do this.
     * <p>
     * new FinalizableDelegatedExecutorService
     * (new ThreadPoolExecutor(1, 1,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>())); 等价于 newFixedThreadPool(1)
     * 多了一个队列 可以一直放
     *
     * 只有executorService这些方法 包装了起来, 没有可供monitor的方法
     * 里面的方法调用的都是ThreadPool的方法, 包装了一层, 外部无法调用
     *
     * Exception in thread "main" java.lang.ClassCastException:
     *  java.util.concurrent.Executors$FinalizableDelegatedExecutorService
     *      cannot be cast to java.util.concurrent.ThreadPoolExecutor
     *
     * @throws InterruptedException
     */
    private static void useSinglePool() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS, 这个参数其实没有意义 因为不会回收线程 核心线程=最大线程=10
     * new LinkedBlockingQueue<Runnable>());
     * <p>
     * public LinkedBlockingQueue() {
     * this(Integer.MAX_VALUE);
     * }
     */
    private static void useFixedSizePool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    /**
     * 非常短的任务才使用cachedThreadPool
     * These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * <p>
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * 60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>(),
     * threadFactory)
     */
    private static void useCachedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        executorService.execute(() -> System.out.println("=========="));

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

    }


}
