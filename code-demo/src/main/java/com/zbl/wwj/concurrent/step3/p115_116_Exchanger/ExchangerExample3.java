package com.zbl.wwj.concurrent.step3.p115_116_Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/11
 * @Description:
 * @Version: $
 */
public class ExchangerExample3 {

    /**
     * exchange(V v )
     * v: indicate the object the current thread wanted transfer.
     * r: indicate the object the other thread(pair) return object.
     * <p>
     * NOTE:
     * 1. if the pair thread not reached change point, the thread will blocked.
     * 2. use the  {@link Exchanger} must be paired.
     *
     * @param args
     */
    public static void main(String[] args) {
        //Exchanger 传的对象 是传的是同一个对象,  会有线程安全问题
        //Exchanger 可以重复使用
        final Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");

            // b 不知道这边超时了 所以b会一致等下去
            AtomicReference<Integer> v = new AtomicReference<>(1);
            while (true) {
                try {
                    v.set(exchanger.exchange(v.get()));
                    System.out.println("Thread A has value: " + v.get());
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");

            // b 不知道这边超时了 所以b会一致等下去
            AtomicReference<Integer> v = new AtomicReference<>(2);
            while (true) {
                try {
                    v.set(exchanger.exchange(v.get()));
                    System.out.println("Thread B has value: " + v.get());
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
