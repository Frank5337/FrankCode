package com.zbl.wwj.concurrent.step1.P16;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/1 12:24
 * @Description:
 * @Version: $
 */
public class ThreadCloseGraceful2 {

    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
//                try {
//                    Thread.sleep(1_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    break;//return
//                }
                if (Thread.interrupted()) {
                    break;
                }
            }

            System.out.println("bbb");
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
