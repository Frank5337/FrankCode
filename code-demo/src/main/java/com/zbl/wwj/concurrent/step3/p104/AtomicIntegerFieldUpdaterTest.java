package com.zbl.wwj.concurrent.step3.p104;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/8
 * @Description:
 * @Version: $
 */
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe ne = new TestMe();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    int v = updater.getAndIncrement(ne);
                    System.out.println(Thread.currentThread().getName() + " => " + v);
                }
            }).start();
        }
    }

    static class TestMe {
        volatile int i;
    }
}
