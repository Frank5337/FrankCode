package com.zbl.concurrent.wwj.step1.p26;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 13:48
 * @Description:
 * @Version: $
 */
public class ProductConsumerVersion3 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("P" + ++i);
                isProduced = true;
                LOCK.notify();
            }

        }
    }

    public void consumer() {
        synchronized (LOCK) {
            if (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("C" + i);
                isProduced = false;
                LOCK.notify();
            }

        }
    }

    public static void main(String[] args) {
        ProductConsumerVersion3 pc = new ProductConsumerVersion3();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(15_00);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pc.produce();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(15_00);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pc.consumer();
                }
            }
        }).start();
    }

}
