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
public class PhaserExample4 {

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
        //重复使用 计数器 循环使用
//        final Phaser phaser = new Phaser(1);
//        System.out.println(phaser.getPhase());
//
//        phaser.arriveAndAwaitAdvance();
//        System.out.println(phaser.getPhase());
//
//        phaser.arriveAndAwaitAdvance();
//        System.out.println(phaser.getPhase());
//
//        phaser.arriveAndAwaitAdvance();
//        System.out.println(phaser.getPhase());

//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties());
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties());

//        System.out.println(phaser.getArrivedParties());//0
//        System.out.println(phaser.getUnarrivedParties());//1

//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());//10 + 1
//        System.out.println(phaser.getArrivedParties());//0
//        System.out.println(phaser.getUnarrivedParties());//10 + 1
//
//        new Thread(phaser::arriveAndAwaitAdvance).start();
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println("===========================");
//        System.out.println(phaser.getRegisteredParties());//10 + 1
//        System.out.println(phaser.getArrivedParties());//1
//        System.out.println(phaser.getUnarrivedParties());//10

        final Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return true;
                //Alex I am start and the phaser 0
                //Jack I am start and the phaser 0
                //Alex I am end
                //Jack I am end
                //Alex I am start and the phaser -2147483647//已经不生效了
                //Alex I am end
                //0
                //2

//                return false;
                //Jack I am start and the phaser 0
                //Alex I am start and the phaser 0
                //Alex I am end
                //Jack I am end
                //Alex I am start and the phaser 1
                //1
                //1
            }
        };

        new OnAdvanceTasks("Alex", phaser).start();
        new OnAdvanceTasks("Jack", phaser).start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());
    }

    static class OnAdvanceTasks extends Thread {

        private final Phaser phaser;

        public OnAdvanceTasks(String name, Phaser phaser) {
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " I am start and the phaser " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " I am end");
            System.out.println(phaser.isTerminated());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getName().startsWith("A")) {
                System.out.println(Thread.currentThread().getName() + " I am start and the phaser " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + " I am end");
            }

            System.out.println(phaser.isTerminated());

        }
    }


}
