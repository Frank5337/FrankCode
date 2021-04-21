package com.zbl.wwj.concurrent.step3.p129_132_Phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/19 19:30
 * @Description: 打断
 * @Version: $
 */
public class PhaserExample8 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * awaitAdvance can decremental the arrived parities?
     *
     * @param args
     */
    public static void main(String[] args) {
//        final Phaser phaser = new Phaser(3);
//        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
//        thread.start();
//        System.out.println("============================");
//        slSeconds();
//        thread.interrupt();
//        System.out.println("thread.interrupt ===");

//        final Phaser phaser = new Phaser(3);
//        Thread thread = new Thread(() -> {
//            try {
//                //Awaits the phase of this phaser to advance from the given phase
//                //value, throwing {@code InterruptedException} if interrupted
//                //while waiting, or returning immediately if the current phase is
//                //not equal to the given phase value or this phaser is
//                //terminated.
//                phaser.awaitAdvanceInterruptibly(10);//不一样 立即结束 不会被block
//                System.out.println("****************************");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread.start();
//
//        System.out.println("============================");
//        slSeconds();
//        thread.interrupt();
//        System.out.println("thread.interrupt ===");

        final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                //等 5 秒  5秒过了超时 TimeOutExp
                //phase 不合理 也会立即结束
                phaser.awaitAdvanceInterruptibly(10, 5, TimeUnit.SECONDS);
                System.out.println("****************************");
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private static void slSeconds() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
