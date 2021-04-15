package com.zbl.wwj.concurrent.step3.p95_105_Atomic.p95;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/6 17:05
 * @Description:
 * @Version: $
 */
public class AtomicIntegerTest {

    /**
     * 1.内存可见性
     * 2.防止指令重排序
     * cpu广播?
     * <p>
     * 不能保证原子性
     */
    private static volatile int value;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            int x =0;
//            while (x < 500) {
//                int tmp = value;
//                System.out.println(Thread.currentThread().getName() + ":" +tmp);
//                value++;
//                x++;
//                set.add(tmp);
//                /**
//                 * value = value + 1
//                 * 1) get value from main memory to local memory
//                 * 2) add 1 -> x
//                 * 3) assign the value to x
//                 * 4) flush to main memory
//                 */
//            }
//        });
//        Thread t2 = new Thread(() -> {
//            int x =0;
//            while (x < 500) {
//                int tmp = value;
//                System.out.println(Thread.currentThread().getName() + ":" +tmp);
//                value++;
//                x++;
//                set.add(tmp);
//            }
//        });
//        Thread t3 = new Thread(() -> {
//            int x =0;
//            while (x < 500) {
//                int tmp = value;
//                System.out.println(Thread.currentThread().getName() + ":" +tmp);
//                value++;
//                x++;
//                set.add(tmp);
//            }
//        });


        AtomicInteger integer = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                int tmp = integer.getAndIncrement();
                System.out.println(Thread.currentThread().getName() + ":" + tmp);
                x++;
                set.add(tmp);
            }
        });
        Thread t2 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                int tmp = integer.getAndIncrement();
                System.out.println(Thread.currentThread().getName() + ":" + tmp);
                x++;
                set.add(tmp);
            }
        });
        Thread t3 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                int tmp = integer.getAndIncrement();
                System.out.println(Thread.currentThread().getName() + ":" + tmp);
                x++;
                set.add(tmp);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(set.size());
    }

}
