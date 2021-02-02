package com.zbl.concurrent.wwj.step1.P16;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/1 12:31
 * @Description: 强制打断线程
 * @Version: $
 */
public class ThreadCloseForce {

    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                //collection
            }
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

        worker.interrupt();
    }
}
