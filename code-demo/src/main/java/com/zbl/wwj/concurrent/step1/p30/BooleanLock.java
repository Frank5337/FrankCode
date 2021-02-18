package com.zbl.wwj.concurrent.step1.p30;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 15:42
 * @Description:
 * @Version: $
 */
public class BooleanLock implements LOCK {

    private boolean initValue;

    private volatile Collection<Thread> blockedCollection = new ArrayList<>();

    private Thread currentThread;

    //The initValue is true indicated the lock have be get.
    //The initValue is false indicated the lock is free. (other thread can get this).
    public BooleanLock() {
        this.initValue = false;
    }


    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedCollection.remove(Thread.currentThread());
        this.initValue = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            //你玩赖, 我也玩赖
            lock();
        }

        //应该什么时候结束
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining >= 0) {
                throw new TimeOutException("Time out");
            }
            blockedCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = System.currentTimeMillis() - endTime;
        }
        //抢到锁,  这个锁是我家的 hei hei
        blockedCollection.remove(Thread.currentThread());
        initValue = true;
        currentThread = Thread.currentThread();

    }

    @Override
    public synchronized void unLock() {
        if (currentThread == Thread.currentThread()) {
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + "  release the lock monitor").ifPresent(System.out::println);
            System.out.println();
            this.notifyAll();
        }

    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedCollection.size();
    }
}
