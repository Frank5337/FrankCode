package com.zbl.wwj.concurrent.step3.p132_Executor;

import java.util.concurrent.*;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/21 12:27
 * @Description:
 * @Version: $
 */
public class ThreadPoolExecutorBuild {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) buildThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = -1;
        long start = System.currentTimeMillis();
        while (true) {
            if (activeCount != executorService.getActiveCount() || queueSize != executorService.getQueue().size()) {
                long end = System.currentTimeMillis();
                System.out.println("need time: " + (end - start));
                start = end;
                System.out.println("getActiveCount " + executorService.getActiveCount());
//                System.out.println("getCorePoolSize " + executorService.getCorePoolSize());
                System.out.println("getQueue.size " + executorService.getQueue().size());
//                System.out.println("getMaximumPoolSize " + executorService.getMaximumPoolSize());
                activeCount = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();
                System.out.println("====================================================");
            }
        }
    }

    /**
     * Testing point.
     * <p>
     * 1. coreSize = 1, MaxSize = 2 blockingQueue is empty. what happen when submit 3 task ?
     * 2. coreSize = 1, MaxSize = 2 blockingQueue size = 5.what happen when submit 7 task ?
     * 3. coreSize = 1, MaxSize = 2 blockingQueue size = 5.what happen when submit 8 task ?
     * </p>
     * <p>
     * <p>
     * int corePoolSize,                 核心线程数
     * int maximumPoolSize,              最大线程数
     * long keepAliveTime,               保持时间
     * TimeUnit unit,                    保持时间的单位
     * BlockingQueue<Runnable> workQueue,工作队列
     * RejectedExecutionHandler handler  拒绝策略
     */
    private static ExecutorService buildThreadPoolExecutor() {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), (ThreadFactory) Thread::new,
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done.");

        executorService.execute(() -> slSeconds(10));
        executorService.execute(() -> slSeconds(10));
        executorService.execute(() -> slSeconds(100));//队列满了才会增加线程
        //executorService.execute(() -> slSeconds(100));//会被拒绝策略接管  new ThreadPoolExecutor.AbortPolicy()
        return executorService;
    }

    private static void slSeconds(long seconds) {
        try {
            System.out.println("sleep " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
