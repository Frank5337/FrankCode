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
public class PhaserExample5 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * running
     * <p>
     * bicycle
     * <p>
     * long jump
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//            final Phaser phaser = new Phaser();
        //到达并且等待   等待计数器到0
//            new Thread(phaser::arriveAndAwaitAdvance).start();
//
//        TimeUnit.SECONDS.sleep(4);
        final Phaser phaser = new Phaser(5);
        for (int i = 0; i < 4; i++) {
            new ArriveTask(phaser, i).start();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("The phase 1 work finished done.");
    }

    private static class ArriveTask extends Thread {
        private final Phaser phaser;

        public ArriveTask(Phaser phaser, int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " start working.");

            slSeconds();

            System.out.println(getName() + " the work arrive.");
            phaser.arrive();

            slSeconds();
            System.out.println(getName() + " keep to do other thing.");
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
