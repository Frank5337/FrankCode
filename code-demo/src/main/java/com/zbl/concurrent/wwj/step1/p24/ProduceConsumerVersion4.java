package com.zbl.concurrent.wwj.step1.p24;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/6
 * @Description:
 * @Version: $
 */
public class ProduceConsumerVersion4 {

    private int i = 0;

    private int c = 0;

    private volatile boolean isProduced = false;

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private void produce() {
        if (i == 200) {
            return;
        }
        try {
            lock.lock();
            if (!isProduced) {
                System.out.println(Thread.currentThread() + "P->" + ++i);  //就这里比上面的程序多了
                condition.await();
            }
            isProduced = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void consume() {
        try {
            lock.lock();
            if (isProduced) {
                if (i == 1) {
                    c = 1;
                }
                if (i == c) {
                    System.out.println(Thread.currentThread() + "C->" + i);  //就这里比上面的程序多了
                    c++;
                }
                condition.await();
            }
            isProduced = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion4 pc = new ProduceConsumerVersion4();

        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    try {
                        pc.produce();
                        Thread.sleep(1_00);
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
                        Thread.sleep(1_00);
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
