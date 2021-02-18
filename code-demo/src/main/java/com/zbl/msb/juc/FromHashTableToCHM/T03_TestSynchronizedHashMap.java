package com.zbl.msb.juc.FromHashTableToCHM;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: zbl
 * @Date: Created in 15:39 2020/7/25
 * @Description:
 * @Version: $
 * <p>
 * 用synchronizedMap 方法给我们的HashMap加锁,
 * 它的源码上自己做了一个Object, 然后每次都是SynchronizedObj,
 * 严格来说, 和HashTable效率相差不大
 * <p>
 * 9899
 * 43714
 */
public class T03_TestSynchronizedHashMap {

    private static final int COUNT = 1_000_000;

    private static final int THREAD_COUNT = 100;

    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<>());

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
