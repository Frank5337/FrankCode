package com.zbl.wwj.concurrent.step3.p97;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/7 12:39
 * @Description:
 * @Version: $
 */
public class CompareAndSetLock {

    private final AtomicInteger v = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException {
        boolean success = v.compareAndSet(0, 1);
        if (!success) {
            throw new GetLockException("get the lock faild exception");
        }
        lockedThread = Thread.currentThread();
    }

    public void unLock() {
        if (0 == v.get()) {
            return;
        }
        if (lockedThread == Thread.currentThread()) {
            v.compareAndSet(1, 0);
        }


    }
}
