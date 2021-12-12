package com.zbl.msb.juc;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 18:10 2020/7/26
 * @Description: LinkedBlockingQueue
 * 用链表实现的Blocking, 是一个无界队列, 就是它可以一直装到你的内存满了为止, 一直添加
 * <p>
 * BlockingQueue在Queue的基础上又添加了两个方法, 这两个方法一个叫put, 一个叫take
 * 这两个方法真真正正实现了阻塞,
 * put往里面装, 如果装满了的话, 这个线程会组设主,
 * take往外取, 如果空了的话, 线程阻塞住,
 */
public class T05_LinkedBlockingQueue {

    static BlockingQueue<String> strs = new LinkedBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    strs.put("a" + i);//如果满了 就会等待
                    System.out.println("put a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                    if (i == 10) {
                        System.out.println("休眠 10 秒");
                        TimeUnit.SECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take - " + strs.take()); //如果空了 就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }

    }

    @Test
    public void test01() throws Exception {
//        long now = System.currentTimeMillis();
//        Date date O= new Date(now);
//        System.out.println(date);
//        Date date1 = new Date(now - 30 * 60 * 1000);
//        System.out.println(date1);
        Map map = new HashMap();
        Map dbIdMap = new HashMap();
        map.put("dbId", dbIdMap);
        dbIdMap.put("x1", "x");
        dbIdMap.put("x2", "x");
        dbIdMap.put("x3", "x");
        if (dbIdMap.get("x1") != null) {
//            dbIdMap.put("x1", x+1);
        }
    }


}
