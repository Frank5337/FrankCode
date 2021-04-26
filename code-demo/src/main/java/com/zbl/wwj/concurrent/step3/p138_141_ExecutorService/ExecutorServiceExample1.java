package com.zbl.wwj.concurrent.step3.p138_141_ExecutorService;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/26
 * @Description:
 * @Version: $
 */
public class ExecutorServiceExample1 {

    /**
     * The demo for class {@link java.util.concurrent.ExecutorService}
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        isShutDown();
//        isTerminated();
//        executeRunnableError();
        executeRunnableTask();
    }

    /**
     * Question:
     * <p>
     * When invoked the shutdown method, can execute the new runnable ?
     * Answer:
     * java.util.concurrent.RejectedExecutionException
     * No the ExecutorService will rejected after shutdown.
     * </p>
     * <p>
     * {@link ExecutorService#shutdown()}
     */
    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        executorService.execute(() -> {
            System.out.println("I will be executed after shutdown ?");
        });
    }

    /**
     * {@link ExecutorService#isTerminated()}
     * {@link ThreadPoolExecutor#isTerminating()}
     * 油漆桶设计模式/包装器设计模式
     */
    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());//终止中

    }

    private static void executeRunnableError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> System.out.println(1 / 0)));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("=================================");

    }

    /**
     * <pre>
     *
     *
     *                                    | ---->
     * send request----> store db--->10-> | ---->
     *                                    | ---->
     *
     * 典型的模板方法
     * </pre>
     */
    private static void executeRunnableTask() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(
                new MyTask(i) {
                    @Override
                    protected void doInit() {
                        //do nothing
                    }

                    @Override
                    protected void doExecute() {
                        if (i % 3 == 0) {
                            int tmp = i / 0;
                        }

                    }

                    @Override
                    protected void done() {
                        System.out.println("The no: " + i + " SUCCESS, update status to DONE");
                    }

                    @Override
                    protected void error(Throwable cause) {
                        System.out.println("The no: " + i + " failed, update status to ERROR");
                    }
                }
        ));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("=================================");
    }

    private abstract static class MyTask implements Runnable {
        protected final int no;

        public MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }


        protected abstract void doInit();

        protected abstract void doExecute();

        protected abstract void done();

        protected abstract void error(Throwable cause);
    }

    private static class MyThreadFactory implements ThreadFactory {

        private static final AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("Thread thread " + t.getName() + "execute failed.");
                cause.printStackTrace();
                System.out.println("===============================================");
            });
            return thread;
        }
    }

}
