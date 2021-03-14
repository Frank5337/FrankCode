package com.zbl.wwj.concurrent.step2.p56;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/14
 * @Description: 读写锁
 * @Version: $
 */
public class ReadWriteLock {

    /**
     * 当前几个线程正在读
     */
    public int readingReaders = 0;

    /**
     * 几个线程读等待
     */
    public int waitingReaders = 0;

    /**
     * 正在写的线程 最多1
     */
    public int writingWriters = 0;

    /**
     * 写等待的线程
     */
    public int waitingWriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnLock() {
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnLock() {
        this.writingWriters--;
        this.notifyAll();
    }

}
