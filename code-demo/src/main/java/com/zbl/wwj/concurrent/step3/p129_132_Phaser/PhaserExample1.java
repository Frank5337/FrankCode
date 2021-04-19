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
public class PhaserExample1 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        //重复使用 计数器 循环使用
        final Phaser phaser = new Phaser();

        IntStream.rangeClosed(1, 5).boxed().map(i -> phaser).forEach(Task::new);

        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("The worker[" + Thread.currentThread().getName() + "]");
    }

    static class Task extends Thread {

        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("The worker[" + Thread.currentThread().getName() + "]");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //到达并且等待前进
            phaser.arriveAndAwaitAdvance();

        }
    }
}
