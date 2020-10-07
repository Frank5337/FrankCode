package com.zbl.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zbl
 * @Date: Created in 2020/9/25
 * @Description:
 * @Version: $
 */
public class DemoTest06 {
    volatile List list = new ArrayList();

    public synchronized void add(int i) {
        list.add(i);
    }

    public synchronized int size() {
        return list.size();
    }

    public static void main(String[] args) {
        DemoTest06 test = new DemoTest06();
        new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                test.add(i);
                System.out.println("t1 " + i);
                if (test.size() == 50) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (test.size() == 50) {
                    break;
                }
            }
            System.out.println("t2结束------------------------------------");
        }, "t2").start();
    }

    @Test
    public void test01() throws Exception {
        Set set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        Set set2 = new HashSet<>();
        set2.add("d");
        set2.add("b");
        set2.add("c");
        set.retainAll(set2);
        System.out.println(set);
        System.out.println(set2);
    }

}
