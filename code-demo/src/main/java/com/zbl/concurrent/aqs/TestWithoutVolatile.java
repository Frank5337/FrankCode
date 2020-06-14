package com.zbl.concurrent.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 17:24 2020/6/14
 * @Description:
 */
public class TestWithoutVolatile {
    volatile List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {
        TestWithoutVolatile t = new TestWithoutVolatile();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                if (t.size() != 5) {
                    try {
                        lock.wait();
                        System.out.println("t2 结束");
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    t.add(new Object());
                    System.out.println("add " + i);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                    if (t.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, "t1").start();


    }
}
