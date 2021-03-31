package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 15:11
 * @Description:
 * @Version: $
 */
public class SchedulerThead extends Thread {

    private final ActivationQueue activationQueue;

    public SchedulerThead(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request) {
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true) {
            activationQueue.take().execute();
        }
    }
}
