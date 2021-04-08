package com.zbl.wwj.concurrent.step3.p102;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/8
 * @Description:
 * @Version: $
 */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                boolean b = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + " " + b);
                boolean b2 = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + " " + b2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                int stamp = atomicRef.getStamp();
                System.out.println("Before sleep stamp: " + stamp);
                TimeUnit.SECONDS.sleep(2);
                boolean succ = atomicRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(succ);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicRef.getReference());
    }
}
