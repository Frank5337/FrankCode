package com.zbl.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: zbl
 * @Date: 17:13 2020/7/26
 * @Description: 写时复制,
 * CopyOnWrite写的时候不加锁, 读的时候也不加锁,
 * 写的时候回在原来的基础上拷贝一个, 拷贝的时候扩展出一个新的元素出来
 * 然后把新添加的这个扔到这个元素最后的位置,
 * 最后老容器指向新的
 * <p>
 * 这里只是写了一个写线程, 没有模拟读线程, 这个写时复制, 写的效率比较低, 因为每次都要复制
 * 在读多写少的情况下使用CopyOnWrite
 */
public class T02_CopyOnWriteArrayList {

    public static void main(String[] args) {
        List<String> lists =
                //new ArrayList<>();//这个会有并发问题

                //28 27 27 30
                //new Vector();

                //3569 3378 3415
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            Runnable task = () -> {
                for (int j = 0; j < 1000; j++) {
                    lists.add("a" + r.nextInt(10000));
                }
            };
            ths[i] = new Thread(task);
        }

        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    /**
     * 运行并计算时间
     *
     * @param ths
     */
    private static void runAndComputeTime(Thread[] ths) {
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }


}
