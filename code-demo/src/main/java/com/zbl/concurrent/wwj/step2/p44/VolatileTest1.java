package com.zbl.concurrent.wwj.step2.p44;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/16
 * @Description:
 * @Version: $
 */
public class VolatileTest1 {

    /**
     * 加volatile后 会从主内存去拿
     */
    private volatile static int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value update to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update the value to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATED").start();
    }
}
