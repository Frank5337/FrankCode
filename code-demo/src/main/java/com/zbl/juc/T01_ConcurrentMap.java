package com.zbl.juc;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zbl
 * @Date: Created in 17:03 2020/7/25
 * @Description:
 * @Version: $
 */
public class T01_ConcurrentMap {

    public static void main(String[] args) {

        //高并发 380 377 565 358
//        Map<String, String> map = new ConcurrentHashMap<>();

        //高并发且排序 384 416 389 1032 384
//    Map<String, String> map = new ConcurrentSkipListMap<>();

        //线安,效率低 590 541 509 425
//    Map<String, String> map = new Hashtable<>();

        //线不安, 单线程快 并发下有问题 451 -1 414 -2
//    Map<String, String> map = new HashMap<>();

        //有序, 线程不安全, 691 167 434 -14 330 -3
        TreeMap<String, String> map = new TreeMap<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(10000), "a" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(Thread::start);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());
    }


}
