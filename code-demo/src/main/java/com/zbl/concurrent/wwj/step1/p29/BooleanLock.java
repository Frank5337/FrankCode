package com.zbl.concurrent.wwj.step1.p29;

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
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeOutException {

    }

    @Override
    public synchronized void unLock() {
        this.initValue = false;
        Optional.of(Thread.currentThread().getName() + "  release the lock monitor").ifPresent(System.out::println);
        System.out.println();
        this.notifyAll();
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
