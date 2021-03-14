package com.zbl.wwj.concurrent.step2.p56;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/14
 * @Description:
 * @Version: $
 */

/**
 * ReadWriteLock design pattern
 * Reader-Writer design pattern
 */
public class ReadWritLockClient {

    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new WriterWorker(sharedData, "qwerasdf").start();
        new WriterWorker(sharedData, "QWERASDF").start();
        new Thread(() -> {
            while (true) {
                ReadWriteLock lock = sharedData.getLock();
                System.out.println("-------------------------------------");
                System.out.println("readingReaders " + lock.readingReaders);
                System.out.println("waitingReaders " + lock.waitingReaders);
                System.out.println("writingWriters " + lock.writingWriters);
                System.out.println("waitingWriters " + lock.waitingWriters);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "monitor").start();
    }

}
