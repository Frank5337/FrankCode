package com.zbl.msb.concurrent.aqs;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 21:46 2020/6/6
 * @Description: 阶段
 */
public class TestPhaser {

    static Random r = new Random();

    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for (int i = 0; i <5 ; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新娘")).start();
        new Thread(new Person("新郎")).start();
    }

    static void milliSleep(int mill) {
        try {
            TimeUnit.MICROSECONDS.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("所有人都到到齐了! " + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人都吃完了! " + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人都离开了! " + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束, 新郎新娘xx! " + registeredParties);
                    System.out.println();
                    return false;
                case 4:
                    System.out.println("---------------------------------");
                    return true;
                default:
                    return true;
            }

        }
    }

    static class Person implements Runnable {

        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(1000);
            System.out.printf("%s 到达现场! \n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(1000);
            System.out.printf("%s 吃完! \n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(1000);
            System.out.printf("%s 离开! \n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void hug() {
            if ("新娘".equals(name) || "新郎".equals(name)) {
                milliSleep(1000);
                System.out.printf("%s 洞房! \n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }

        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }


}


