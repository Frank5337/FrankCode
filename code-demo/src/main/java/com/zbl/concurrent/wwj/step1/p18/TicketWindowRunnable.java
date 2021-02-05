package com.zbl.concurrent.wwj.step1.p18;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/4 12:28
 * @Description:
 * @Version: $
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private static final int MAX = 500;

    private final Object monitor = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                if (index > MAX) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " 的号码是: " + index++);
            }

        }
    }
}
