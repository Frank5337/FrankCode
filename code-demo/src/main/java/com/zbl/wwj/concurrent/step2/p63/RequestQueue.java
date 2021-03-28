package com.zbl.wwj.concurrent.step2.p63;

import java.util.LinkedList;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/24
 * @Description:
 * @Version: $
 */
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getQueue() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }

}
