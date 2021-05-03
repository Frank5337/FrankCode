package com.zbl.wwj.concurrent.step3.p138_141_ExecutorService;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/30
 * @Description:
 * @Version: $
 */
public class ExecutorServiceExample3 {

    public static void main(String[] args) throws Exception {
//        test(); //刚开始核心线程不是5个 而是0 有任务进来才会创建 创建后不回销毁
//        testAllowsCoreThreadTimeOut();
//        testRemove();
//        testPrestartCoreThread();
//        testPrestartAllCoreThreads();
        testThreadPoolAdThreadPoolAdvices();
    }

    private static void test() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executorService.getActiveCount());
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(executorService.getActiveCount());
    }

    private static void testAllowsCoreThreadTimeOut() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);//运行去回收核心线程
        IntStream.range(0, 5).boxed().forEach( i-> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private static void testRemove() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);//运行去回收核心线程
        IntStream.range(0, 2).boxed().forEach( i-> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        TimeUnit.MILLISECONDS.sleep(20);
        Runnable r = () -> {
            System.out.println("I will never be executed");
        };
        executorService.execute(r);
        TimeUnit.MILLISECONDS.sleep(20);
        executorService.remove(r);
    }

    /**
     * 预启动核心线程
     * @throws InterruptedException
     */
    private static void testPrestartCoreThread() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executorService.getActiveCount());//0

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());//1

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());//2

        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(1);

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());//2

    }

    /**
     * 预启动核心线程
     * @throws InterruptedException
     */
    private static void testPrestartAllCoreThreads() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.setMaximumPoolSize(3);
        System.out.println(executorService.getActiveCount());

        System.out.println(executorService.prestartAllCoreThreads());// 只是按 core 的数量去起线程
        System.out.println(executorService.getActiveCount());

    }


    private static void testThreadPoolAdThreadPoolAdvices() throws InterruptedException {
        ExecutorService executorService = new MyThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), (ThreadFactory) Thread::new,
                new ThreadPoolExecutor.AbortPolicy());

//        executorService.execute(new MyRunnable(1) {
//            @Override
//            public void run() {
//                System.out.println("=============");
//            }
//        });

        executorService.execute(new MyRunnable(1) {
            @Override
            public void run() {
                System.out.println("=============");
                System.out.println(1/0);
            }
        });

    }

    private abstract static class MyRunnable implements Runnable {

        private final int no;

        protected MyRunnable(int no) {
            this.no = no;
        }

        protected int getData() {
            return no;
        }
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("init the " + ((MyRunnable)r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (null == t) {
                System.out.println("succ the " + ((MyRunnable)r).getData());
            } else {
                t.printStackTrace();
            }

        }
    }


}
