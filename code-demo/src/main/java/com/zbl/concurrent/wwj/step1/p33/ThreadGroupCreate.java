package com.zbl.concurrent.wwj.step1.p33;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/10
 * @Description:
 * @Version: $
 */
public class ThreadGroupCreate {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());//main
        System.out.println(Thread.currentThread().getThreadGroup());//java.lang.ThreadGroup[name=main,maxpri=10]
        System.out.println("-------------------------------------");
        //use the name  默认拿当前线程的ThreadGroup main 就是 mainThreadGroup
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                try {
                    System.out.println("t1.sout:  " + getThreadGroup().getName());
                    System.out.println("t1.sout:  " + getThreadGroup().getParent());
                    System.out.println("t1.sout:  " + getThreadGroup().getParent().activeCount());
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();


//        System.out.println("=============================");
        //use the parent and group name
//        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                System.out.println("t2.sout:  " + tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach( t -> {
                    System.out.println("t2.sout:  " + t);
                });
            }
        };
        t2.start();
        System.out.println(tg2.getName());
        System.out.println(tg2.getParent());

    }

}
