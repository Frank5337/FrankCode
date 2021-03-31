package com.zbl.wwj.concurrent.step2.p71;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/30
 * @Description:
 * @Version: $
 */
public class CountDown {

    private final int total;

    private int counter;

    public CountDown(int total) {
        this.total = total;
    }

    public void down() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }
}
