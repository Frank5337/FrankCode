package com.zbl.wwj.concurrent.step2.p44;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/16
 * @Description:
 * @Version: $
 */
public class VolatileTest2 {

    private static volatile int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 50;

    /**
     * 有写的操作  Java会更新主内存
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T1->" + ++INIT_VALUE);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T2->" + ++INIT_VALUE);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-2").start();
    }
}
