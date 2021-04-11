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
public class ExchangerExample2 {

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
        //Exchanger 传的对象 是传的是同一个对象,  会有线程安全问题
        //Exchanger 可以重复使用
        final Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                // b 不知道这边超时了 所以b会一致等下去
                Object a = new Object();
                System.out.println("A - Obj - a " + a);
                Object b = exchanger.exchange(a, 5000, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + " result.   " + b);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished. ");
        }, "A").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                Thread.sleep(1000);
                Object b = new Object();
                System.out.println("B - Obj - b " + b);
                Object a = exchanger.exchange(b);
                System.out.println(Thread.currentThread().getName() + " result.   " + a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished. ");
        }, "B").start();
    }
}
