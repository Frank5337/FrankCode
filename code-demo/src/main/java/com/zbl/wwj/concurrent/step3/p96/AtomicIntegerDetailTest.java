package com.zbl.wwj.concurrent.step3.p96;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/6
 * @Description:
 * @Version: $
 */
public class AtomicIntegerDetailTest {

    @Test
    public void testCreate() throws Exception {
        //create
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());

        i = new AtomicInteger(10);
        System.out.println(i.get());

        //set
        i.set(12);
        System.out.println(i.get());

        i.lazySet(15);
        System.out.println(i.get());

        //i.getAndSet()
        i.getAndSet(12);
        System.out.println(i.get());
        System.out.println("============================");
        AtomicInteger i2 = new AtomicInteger(10);
        int andAdd = i2.getAndAdd(10);
        System.out.println(andAdd);
        System.out.println(i2.get());

        AtomicInteger getAndSet = new AtomicInteger(0);
        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                int v = getAndSet.addAndGet(1);
                System.out.println("ttt " + v);
            }
        }).start();
        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                int v = getAndSet.addAndGet(1);
                System.out.println("ttt " + v);
            }
        }).start();

    }

}
