package com.zbl.wwj.concurrent.step3.p129_132_Phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/19 19:30
 * @Description:
 * @Version: $
 */
public class PhaserExample6 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * awaitAdvance can decremental the arrived parities?
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(6);
//        new Thread(() -> phaser.awaitAdvance(phaser.getPhase())).start();
//        slSeconds();
//        System.out.println(phaser.getArrivedParties());

        //等待所有
//        phaser.awaitAdvance(phaser.getPhase());
//        System.out.println("================");

//        IntStream.rangeClosed(0, 5).boxed().map(i -> phaser).forEach(AwaitAdvanceTask::new);

    }

    private static class AwaitAdvanceTask extends Thread {
        private final Phaser phaser;

        public AwaitAdvanceTask(Phaser phaser, int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            slSeconds();
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " finished work");
        }

    }

    private static void slSeconds() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
