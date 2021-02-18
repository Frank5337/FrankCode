package com.zbl.msb.juc.FromHashTableToCHM;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zbl
 * @Date: Created in 15:39 2020/7/25
 * @Description:
 * @Version: $
 * ConcurrentHashMap, 多线程里面真正使用的,
 * ConcurrentHashMap效率主要提高在读上面, 由于往里插的时候内部做了各种各样的判断,
 * 本来是链表 到了8之后又变成了红黑树, 然后里面各种CAS判断, 所以其插入数据效率要低一些,
 * HashMap HashTable 虽然读效率低, 但是往里面插入的时候做的操作特别少, 然后加个锁往里一插. 具体效率还是要看实际需求
 * <p>
 * 最快的
 * 2165
 * 1418
 */
public class T04_TestConcurrentHashMap {

    private static final int COUNT = 1_000_000;

    private static final int THREAD_COUNT = 100;

    static ConcurrentHashMap<UUID, UUID> m = new ConcurrentHashMap<>();

    private static UUID[] keys = new UUID[COUNT];

    private static UUID[] values = new UUID[COUNT];

    static {
        for (int i = 0; i < COUNT; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;

        //每个线程负责多少
        int gap = COUNT / THREAD_COUNT;

        //每次开始取的位置  0-9 10-19 20-29
        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = 0; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (COUNT / THREAD_COUNT));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);

        // ----------------------------------------------------------

        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10_000_000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}
