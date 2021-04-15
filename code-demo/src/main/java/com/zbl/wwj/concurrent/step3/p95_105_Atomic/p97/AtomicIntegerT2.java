package com.zbl.wwj.concurrent.step3.p95_105_Atomic.p97;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/7 11:24
 * @Description:
 * @Version: $
 */
public class AtomicIntegerT2 {

    private static final CompareAndSetLock c = new CompareAndSetLock();

    public static void main(String[] args) {
        //立即失败
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    doSomething2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void doSomething() throws InterruptedException {
        synchronized (AtomicIntegerT2.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        }
    }

    private static void doSomething2() throws InterruptedException {
        try {
            c.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.unLock();
        }
    }
}
