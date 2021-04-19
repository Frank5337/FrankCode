package com.zbl.wwj.concurrent.step3.p129_132_Phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/19 19:30
 * @Description:
 * @Version: $
 */
public class PhaserExample2 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * running
     *
     * bicycle
     *
     * long jump
     *
     * @param args
     */
    public static void main(String[] args) {
        //重复使用 计数器 循环使用
        final Phaser phaser = new Phaser();

        IntStream.rangeClosed(1, 5).boxed().forEach(i -> {
            new Athletes(i, phaser);
        });

//        phaser.register();
//        phaser.arriveAndAwaitAdvance();
//        System.out.println("The worker[" + Thread.currentThread().getName() + "]");
    }

    static class Athletes extends Thread {

        private final Phaser phaser;

        private final int no;

        public Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
            phaser.register();
            start();
        }

        @Override
        public void run() {

            try {
                System.out.println(no + " start running. [" + Thread.currentThread().getName() + "]");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(phaser.getPhase());
                System.out.println(no + " end running. [" + Thread.currentThread().getName() + "]");

                //到达并且等待前进
                phaser.arriveAndAwaitAdvance();
                System.out.println(no + " start bicycle. [" + Thread.currentThread().getName() + "]");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(phaser.getPhase());
                System.out.println(no + " end bicycle. [" + Thread.currentThread().getName() + "]");

                //到达并且等待前进
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + " start long jump. [" + Thread.currentThread().getName() + "]");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(phaser.getPhase());
                System.out.println(no + " end long jump. [" + Thread.currentThread().getName() + "]");

                //到达并且等待前进
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
