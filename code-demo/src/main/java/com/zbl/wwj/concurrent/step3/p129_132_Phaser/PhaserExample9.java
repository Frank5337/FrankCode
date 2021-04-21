package com.zbl.wwj.concurrent.step3.p129_132_Phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/19 19:30
 * @Description: 打断
 * @Version: $
 */
public class PhaserExample9 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * awaitAdvance can decremental the arrived parities?
     *
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
        thread.start();
        slSeconds();

        System.out.println(phaser.isTerminated());
        //强制终止
        phaser.forceTermination();
        System.out.println(phaser.isTerminated());
    }

    private static void slSeconds() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
