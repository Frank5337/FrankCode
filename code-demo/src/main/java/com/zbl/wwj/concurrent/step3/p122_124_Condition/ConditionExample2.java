package com.zbl.wwj.concurrent.step3.p122_124_Condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/15
 * @Description:
 * @Version: $
 */
public class ConditionExample2 {

    //简单的生产者消费者模型

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = false;

    /**
     * 1. not use the Condition only use lock ? NO
     * 2. the producer get the lock but invoke await method and not jump out the lock statement block
     *    why the consumer can get the lock still? No
     *    //拿到锁 await 没有释放锁, consumer 为什么仍然能拿到锁
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                buildData();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                useData();
            }
        }).start();


    }

    private static void buildData() {
        try {
            lock.lock(); // synchronized key word #monitor enter
            while (noUse) {
                condition.await();  // monitor.wait
            }
            data++;
            Optional.of(Thread.currentThread().getName() + " P: " + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            condition.signal();  // monitor.notify
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // synchronized end #monitor exit
        }
    }

    private static void useData() {
        try {
            lock.lock();
            while (!noUse) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of(Thread.currentThread().getName() + " C: " + data).ifPresent(System.out::println);
            noUse = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
