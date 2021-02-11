package com.zbl.concurrent.wwj.step1.P34;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/10
 * @Description:
 * @Version: $
 */
public class ThreadGroupAPI {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                //while (true) {
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
            }
        };
        System.out.println(tg1.isDestroyed());
        //当最后一个线程/线程组中的线程 死亡的时候 才销毁tg1    (线程组死亡的意思是, 线程组中的线程死亡)
        //设置成守护的好处是 里面的线程执行完成后, 可以自己销毁
        tg1.setDaemon(true);
        t1.start();

        //tg1 是 tg2 的父亲 main是爷爷
        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                //while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
            }
        };
        //tg2.setDaemon(true);
        t2.start();

        //评估他和 他的sub的 活跃的Thread 的数量
        System.out.println(tg1.activeCount());
        //活跃的子group
        System.out.println(tg1.activeGroupCount());
        //确定当前正在运行的线程是否有权修改此线程
        t2.checkAccess();
        //摧毁ThreadGroup 但是要确保没有活跃的线程  如果不是空的 或者已经被destory了
        //tg1.destroy(); //java.lang.IllegalThreadStateException

        System.out.println("======================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        tg1.enumerate(ts1);
        Arrays.asList(ts1).forEach(System.out::println);

        System.out.println("======================");
        Thread[] ts2 = new Thread[tg1.activeCount()];
//         Thread[] ts, Boolean recurse  false 没有子group
        tg1.enumerate(ts2, true);
        Arrays.asList(ts2).forEach(System.out::println);

        System.out.println("======================");

        Thread[] ts3 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts3, true);
        Arrays.asList(ts3).forEach(System.out::println);

        System.out.println("======================");
        //拿出最大的优先级
        System.out.println(tg1.getMaxPriority());

        System.out.println("======================");
        //打断线程组中的所有线程 包括子Group
        //tg1.interrupt();


        //
        //Throwable e = new Throwable();
        //tg2.uncaughtException(t1, e);
        //System.out.println(e);
        System.out.println(tg2.parentOf(tg1));
        System.out.println(tg1.parentOf(tg2));

        Thread.sleep(3000);
        System.out.print("tg1.isDestroyed()");
        System.out.println(tg1.isDestroyed());





    }
}
