package com.zbl.concurrent.wwj.step1.p20;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/5 12:54
 * @Description:
 * @Version: $
 */
public class SynchronizedRunnable implements Runnable {

    private int index = 1;

    //readonly
    private static final int MAX = 500;

    /**
     * 锁是this
     */
    @Override
    public void run() {
        while (true) {
            if (ticket()) {
                break;
            }

        }
    }

    private synchronized boolean ticket() {
        //1.getField
        if (index > MAX) {
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //index ++ -> index + 1
        //get Field index
        //  index + 1
        //set Field index
        System.out.println(Thread.currentThread() + " 的号码是: " + index++);
        return false;
    }
}
