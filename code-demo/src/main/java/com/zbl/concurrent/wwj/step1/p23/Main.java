package com.zbl.concurrent.wwj.step1.p23;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/6
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        otherService.s2();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    deadLock.m1();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    otherService.s2();
                }
            }
        }.start();
    }
}
