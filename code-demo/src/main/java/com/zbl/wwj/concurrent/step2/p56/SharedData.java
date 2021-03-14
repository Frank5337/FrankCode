package com.zbl.wwj.concurrent.step2.p56;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/14
 * @Description:
 * @Version: $
 */
public class SharedData {

    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return this.doRead();
        } finally {
            lock.readUnLock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        } finally {
            lock.writeUnLock();
        }
    }

    private char[] doRead() {
        char[] newChar = new char[buffer.length];
        System.arraycopy(buffer, 0, newChar, 0, buffer.length);
        slowy(50);
        return newChar;
    }

    private void doWrite(char c) {
        Arrays.fill(buffer, c);
        slowy(10);
    }

    private void slowy(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {

        }
    }

    public ReadWriteLock getLock() {
        return lock;
    }
}
