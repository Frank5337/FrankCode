package com.zbl.wwj.concurrent.step2.p70;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/30 14:55
 * @Description:
 * @Version: $
 */
public class ConsumerThread extends Thread{

    private final MessageQueue messageQueue;

    private static final Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue messageQueue, int seq) {
        super("CONSUMER " + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " take a message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
