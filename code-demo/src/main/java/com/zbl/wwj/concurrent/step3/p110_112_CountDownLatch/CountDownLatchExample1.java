package com.zbl.wwj.concurrent.step3.p110_112_CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/10
 * @Description:
 * @Version: $
 */
public class CountDownLatchExample1 {

    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        //(1)
        //(2)
        //(3)
        int[] data = query();
        CountDownLatch latch = new CountDownLatch(data.length);
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i, latch));
        }
//        executor.shutdown();//不会block 只会关闭空闲的
//        executor.awaitTermination(1, TimeUnit.HOURS);//阻塞直到关闭请求后所有任务完成执行，或者发生超时，或者当前线程被中断（以先发生者为准）
        latch.await();
        System.out.println("all work finish");
    }

    static class SimpleRunnable implements Runnable {
        private final int[] data;

        private final int index;

        private final CountDownLatch latch;

        public SimpleRunnable(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int v = data[index];
            if (v % 2 == 0) {
                data[index] = v * 2;
            } else {
                data[index] = v * 10;
            }
            System.out.println(Thread.currentThread().getName() + " " + index + " finished");
            latch.countDown();
        }
    }

    private static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
