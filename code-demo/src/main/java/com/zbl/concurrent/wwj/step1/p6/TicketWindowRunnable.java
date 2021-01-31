package com.zbl.concurrent.wwj.step1.p6;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/23
 * @Description:
 * @Version: $
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private final static int MAX = 50;

    @Override
    public void run() {

        while (index <= MAX) {
            System.out.println(Thread.currentThread() + "的号码是: " + (index++));
        }
    }
}
