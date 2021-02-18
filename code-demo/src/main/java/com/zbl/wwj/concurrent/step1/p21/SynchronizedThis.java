package com.zbl.wwj.concurrent.step1.p21;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/6
 * @Description:
 * @Version: $
 */
public class SynchronizedThis {


    public static void main(String[] args) {
        ThisLock lock = new ThisLock();
        new Thread("t1") {
            @Override
            public void run() {
                lock.m1();
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                lock.m2();
            }
        }.start();
    }


}

class ThisLock {

    private final Object o = new Object();

    public synchronized void m1() {
        try {
            System.out.print("m1.");
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        synchronized (o) {
            try {
                System.out.print("m2.");
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
