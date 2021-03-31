package com.zbl.wwj.concurrent.step2.p70;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/30 14:51
 * @Description:
 * @Version: $ 生产者
 */
public class ProducerThread extends Thread {

    private final MessageQueue messageQueue;

    private static final Random random = new Random(System.currentTimeMillis());

    private static final AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQueue, int seq) {
        super("PRODUCER " + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("Message " + counter.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
