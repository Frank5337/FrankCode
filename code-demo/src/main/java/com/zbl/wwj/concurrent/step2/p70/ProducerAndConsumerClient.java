package com.zbl.wwj.concurrent.step2.p70;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/30 14:57
 * @Description:
 * @Version: $
 */
public class ProducerAndConsumerClient {

    public static void main(String[] args) throws InterruptedException {
        final MessageQueue messageQueue = new MessageQueue();
        System.out.println(messageQueue.getMessageSize());
        Thread.sleep(3000);
        new ProducerThread(messageQueue, 1).start();
        new ProducerThread(messageQueue, 2).start();
        new ProducerThread(messageQueue, 3).start();
        new ConsumerThread(messageQueue, 1).start();
        new ConsumerThread(messageQueue, 2).start();
    }
}
