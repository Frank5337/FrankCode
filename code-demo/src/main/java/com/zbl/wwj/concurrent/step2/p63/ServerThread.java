package com.zbl.wwj.concurrent.step2.p63;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/24
 * @Description:
 * @Version: $
 */
public class ServerThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag) {
            Request request = this.queue.getQueue();
            if (null == request) {
                System.out.println("Received the empty request");
                continue;
            }
            System.out.println("Server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
