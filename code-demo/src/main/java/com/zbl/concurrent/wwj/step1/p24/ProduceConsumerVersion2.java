package com.zbl.concurrent.wwj.step1.p24;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/6
 * @Description:
 * @Version: $
 */
public class ProduceConsumerVersion2 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce() {
        synchronized (LOCK) {
            if (!isProduced) {
                System.out.println("P->" + ++i);
                isProduced = true;
            }
        }
    }

    private void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println("C->" + i);
                isProduced = false;
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();

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
    }
}
