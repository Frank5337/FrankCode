package com.zbl.concurrent.wwj.step1.p12;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/31
 * @Description:
 * @Version: $
 */
public class DaemonThread2 {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread( () -> {
            Thread innerT = new Thread(() -> {
                try {
                    while (true){
                        System.out.println("innerT: " + Thread.currentThread().isAlive());
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("innerT.isAlive: " + innerT.isAlive());
            innerT.setDaemon(true);
            innerT.start();
            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        //t.setDaemon(true);
        t.start();
    }
}
