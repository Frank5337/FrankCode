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
public class PhaserExample3 {

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
    public static void main(String[] args) {
        //重复使用 计数器 循环使用
        final Phaser phaser = new Phaser();

        IntStream.rangeClosed(1, 5).boxed().forEach(i -> {
            new Athletes(i, phaser);
        });
        new InjuredAthletes(6, phaser);

    }

    static class InjuredAthletes extends Thread {
        private final Phaser phaser;

        private final int no;

        public InjuredAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
            phaser.register();
            start();
        }

        @Override
        public void run() {

            try {
                sport(phaser, no + " start running", no + " end running.");

                sport(phaser, no + " start bicycle", no + " end bicycle.");

                System.out.println("Oh shit, I am Injured");
                //动态销毁
                phaser.arriveAndDeregister();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
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
                sport(phaser, no + " start running", no + " end running.");

                sport(phaser, no + " start bicycle", no + " end bicycle.");

                sport(phaser, no + " start long jump", no + " end long jump.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void sport(Phaser phaser, String x, String x2) throws InterruptedException {
        System.out.println(x);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(x2);
        //动态注册
        phaser.arriveAndAwaitAdvance();
    }
}
