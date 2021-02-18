package com.zbl.wwj.concurrent.step1.p24;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/6
 * @Description:
 * @Version: $
 */
public class ProduceConsumerVersion3 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("P->" + ++i);
            isProduced = true;
            LOCK.notify();
        }
    }

    private void consume() {
        synchronized (LOCK) {
            if (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + i);
            isProduced = false;
            LOCK.notify();

        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();

        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    try {
                        pc.produce();
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                while (true) {
                    try {
                        pc.consume();
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

        new Thread("C2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        pc.consume();
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}
