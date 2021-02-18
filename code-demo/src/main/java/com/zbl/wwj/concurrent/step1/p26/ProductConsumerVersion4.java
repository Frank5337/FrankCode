package com.zbl.wwj.concurrent.step1.p26;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 13:48
 * @Description:
 * @Version: $
 */
public class ProductConsumerVersion4 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + ++i);
            isProduced = true;
            LOCK.notifyAll();

        }
    }

    public void consumer() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + i);
            isProduced = false;
            LOCK.notifyAll();

        }
    }

    public static void main(String[] args) {
        ProductConsumerVersion4 pc = new ProductConsumerVersion4();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pc.produce();
            }
        }, "P1").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pc.produce();
            }
        }, "P2").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pc.consumer();
            }
        }, "C1").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pc.consumer();
            }
        }, "C2").start();
    }


}
