package com.zbl.wwj.concurrent.step1.p29;

import java.util.Collection;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/7 15:39
 * @Description:
 * @Version: $
 */
public interface LOCK {

    static class TimeOutException extends Exception {

        public TimeOutException(String message) {
            super(message);
        }

    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unLock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();

}
