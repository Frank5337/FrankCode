package com.zbl.wwj.concurrent.step3.p106_108_unsafe.p106;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/9 17:14
 * @Description:
 * @Version: $
 */
public class UnsafeTest {

    /**
     * Java is a safe programming Language and prevents programmer
     * from doing a lot of stupid mistakes,
     * most of which based on memory management.
     * But, there is a way to do such mistakes intentionally,
     * using Unsafe class.
     *
     * @param args
     */
    public static void main(String[] args) {
//        Unsafe unsafe = Unsafe.getUnsafe();
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
    }

    @Test
    public void test01() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(1000);
        //竞争条件
        /**
         * StupidCounter
         * Counter result: 9979299
         * time passed in ms : 235
         *
         * SynCounter
         * Counter result: 10000000
         * time passed in ms : 572
         *
         * LockCounter
         * Counter result: 10000000
         * time passed in ms : 560
         *
         * AtomicCounter
         * Counter result: 10000000
         * time passed in ms : 471
         *
         * CasCounter
         * Counter result: 10000000
         * time passed in ms : 730
         *
         */
//        Counter counter = new StupidCounter();
//        Counter counter = new SynCounter();
//        Counter counter = new LockCounter();
//        Counter counter = new AtomicCounter();
        Counter counter = new CasCounter();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            service.submit(new CounterRunnable(counter, 10000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());
        System.out.println("time passed in ms : " + (end - start));
    }

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface Counter {
        void increment();

        long getCounter();
    }

    static class StupidCounter implements Counter {
        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter {
        private long counter = 0;

        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class SynCounter implements Counter {
        private long counter = 0;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter {
        private AtomicLong counter = new AtomicLong();

        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    static class CasCounter implements Counter {

        private volatile long counter = 0;

        private Unsafe unsafe;

        private long offset;

        CasCounter() throws Exception {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            while (!unsafe.compareAndSwapLong(this, offset,current, current+1)) {
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class CounterRunnable implements Runnable {
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }

}
