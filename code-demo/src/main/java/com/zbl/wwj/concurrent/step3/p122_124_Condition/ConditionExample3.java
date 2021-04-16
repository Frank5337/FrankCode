package com.zbl.wwj.concurrent.step3.p122_124_Condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/17
 * @Description:
 * @Version: $
 */
public class ConditionExample3 {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition PRODUCE_COND = lock.newCondition();

    private static final Condition CONSUMER_COND = lock.newCondition();

    private static final LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private static final int MAX_CAPACITY = 100;

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 6).boxed().forEach(ConditionExample3::beginProduce);
        IntStream.range(0, 3).boxed().forEach(ConditionExample3::beginConsumer);
        //            if (!isHeldExclusively())
        //                throw new IllegalMonitorStateException();
//        for (; ;) {
//            TimeUnit.SECONDS.sleep(5);
//            System.out.println("====================================");
//            System.out.println("PRODUCE_COND.getWaitQueueLength" + lock.getWaitQueueLength(PRODUCE_COND));
//            System.out.println("CONSUMER_COND.getWaitQueueLength" + lock.getWaitQueueLength(CONSUMER_COND));
//            System.out.println("PRODUCE_COND.hasWaiters" + lock.hasWaiters(CONSUMER_COND));
//            System.out.println("CONSUMER_COND.hasWaiters" + lock.hasWaiters(CONSUMER_COND));
//            System.out.println("====================================");
//        }
    }

    private static void beginProduce(int i) {
        new Thread(() -> {
            for (; ; ) {
                ConditionExample3.produce();
                sleep(2);
            }
        }, "P-" + i).start();
    }

    private static void beginConsumer(int i) {
        new Thread(() -> {
            for (; ; ) {
                ConditionExample3.consumer();
                sleep(2);
            }
        }, "C-" + i).start();
    }

    private static void produce() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                PRODUCE_COND.await();
            }
            System.out.println("PRODUCE_COND.getWaitQueueLength" + lock.getWaitQueueLength(PRODUCE_COND));
            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-P-" + value);
            TIMESTAMP_POOL.addLast(value);
            CONSUMER_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consumer() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUMER_COND.await();
            }
            long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C-" + value);
            PRODUCE_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
