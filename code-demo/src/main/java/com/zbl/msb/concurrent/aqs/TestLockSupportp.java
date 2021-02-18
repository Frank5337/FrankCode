package com.zbl.msb.concurrent.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: zbl
 * @Date: 17:24 2020/6/14
 * @Description:
 */
public class TestLockSupportp {

    volatile List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        TestLockSupportp t = new TestLockSupportp();
        Thread t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("t2 结束");
        }, "t2");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add " + i);
                if (t.size() == 5) {
                    LockSupport.unpark(t2);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1");

        t2.start();
        t1.start();

    }
}
