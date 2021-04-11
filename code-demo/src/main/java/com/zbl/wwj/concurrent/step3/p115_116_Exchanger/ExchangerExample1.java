package com.zbl.wwj.concurrent.step3.p115_116_Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class ExchangerExample1 {

    /**
     * exchange(V v )
     *      v: indicate the object the current thread wanted transfer.
     *      r: indicate the object the other thread(pair) return object.
     *
     *      NOTE:
     *          1. if the pair thread not reached change point, the thread will blocked.
     *          2. use the  {@link Exchanger} must be paired.
     * @param args
     */
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                // b 不知道这边超时了 所以b会一致等下去
                String result = exchanger.exchange("I am come from T-A", 500, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + " result. " + result);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished. ");
        }, "A").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                Thread.sleep(1000);
                String result = exchanger.exchange("I am come from T-B");
                System.out.println(Thread.currentThread().getName() + " result. " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished. ");
        }, "B").start();
    }
}
