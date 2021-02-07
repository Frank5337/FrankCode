package com.zbl.concurrent.wwj.step1.p30;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 16:07
 * @Description:
 * @Version: $
 */
public class SynchronizedProblem {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            run();
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            run();
        });
        t2.start();

        Thread.sleep(2000);
        t2.interrupt();
        // t2 已经是打断状态, 但是中断不了
        System.out.println(t2.isInterrupted());
    }

    private static synchronized void run() {

        System.out.println(Thread.currentThread());
        while (true) {

        }
    }
}
