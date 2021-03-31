package com.zbl.wwj.concurrent.step2.p77;

import java.util.LinkedList;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 15:07
 * @Description:
 * @Version: $
 */
public class ActivationQueue {

    private static final int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request) {
        while (methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take() {
        while (methodQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }

}
