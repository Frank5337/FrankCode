package com.zbl.wwj.concurrent.step3.p105;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/8
 * @Description:
 * @Version: $
 */
public class ATFUTest {

    private volatile int i;

    private AtomicInteger j = new AtomicInteger();

    private AtomicIntegerFieldUpdater<ATFUTest> t = AtomicIntegerFieldUpdater.newUpdater(ATFUTest.class, "i");

    public void update(int newValue) {
        t.compareAndSet(this, i, newValue);
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) {
        ATFUTest test= new ATFUTest();
        test.update(10);
        System.out.println(test.get());
    }
}
