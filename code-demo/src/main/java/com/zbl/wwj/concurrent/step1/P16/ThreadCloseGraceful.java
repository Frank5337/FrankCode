package com.zbl.wwj.concurrent.step1.P16;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/1 12:24
 * @Description:
 * @Version: $
 */
public class ThreadCloseGraceful {

    private static class Worker extends Thread {

        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {

            }
        }

        public void shutdown() {
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shutdown();
    }
}
