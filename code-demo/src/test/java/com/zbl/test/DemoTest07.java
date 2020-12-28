package com.zbl.test;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2020/9/25
 * @Description:
 * @Version: $
 */
public class DemoTest07 {

    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("a");
                    if (i == 5) {
                        try {
                            System.out.println("-------------------------------------");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("b");
                }
            }
        }).start();

    }

    @Test
    public void test01() throws Exception {
        Timestamp timestamp = new Timestamp(24 * 3600);
        System.out.println(timestamp);

        System.out.println(1 << 3);
        System.out.println(8 >> 3);


    }

    @Test
    public void test02() throws Exception {
        String commodityIds = "1,2,3,4";
        String[] split = commodityIds.split(",");
        List<String> list = Arrays.asList(split);
        System.out.println(list);
    }

    private static final AtomicInteger counter = new AtomicInteger(-1);

    @Test
    public void test03() throws Exception {
        System.out.println(counter.get());
        int index = counter.getAndIncrement() % 2;
        System.out.println(index);
        System.out.println(counter.get());
    }

    @Test
    public void test04() throws Exception {
        Long l = 128L;

        Long l2 = 128L;
        System.out.println(l.equals(l2));
    }
}
