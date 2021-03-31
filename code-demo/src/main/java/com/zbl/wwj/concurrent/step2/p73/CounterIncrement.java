package com.zbl.wwj.concurrent.step2.p73;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 10:56
 * @Description:
 * @Version: $
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;

    private Random r = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(r.nextInt(1000));
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } finally {
            clean();
        }
    }

    private void clean() {
        System.out.println("do some clean work for the second phase. counter = " + counter);
    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }

}


