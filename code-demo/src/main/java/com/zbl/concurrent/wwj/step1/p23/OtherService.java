package com.zbl.concurrent.wwj.step1.p23;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/6
 * @Description:
 * @Version: $
 */
public class OtherService {

    private final Object lock = new Object();

    private DeadLock deadLock;

    public void s1() {
        synchronized (lock) {
            System.out.println("s1");
        }
    }


    public void s2() {
        synchronized (lock) {
            System.out.println("s2");
            deadLock.m2();
        }
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
