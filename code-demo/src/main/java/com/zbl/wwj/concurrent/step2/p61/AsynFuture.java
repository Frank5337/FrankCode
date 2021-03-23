package com.zbl.wwj.concurrent.step2.p61;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/23
 * @Description:
 * @Version: $
 */
public class AsynFuture<T> implements Future<T> {

    private volatile boolean DONE = false;

    private T result;

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.DONE = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!DONE) {
                this.wait();
            }
        }
        return result;
    }
}
