package com.zbl.concurrent.wwj.p8;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/24
 * @Description:
 * @Version: $
 *
 * 构造
 * Thread();
 * Thread(Runnable target);
 * Thread(String name);
 * Thread(Runnable target, String name);
 *
 * Allocates a new Thread Object; 开辟一个线程对象
 */
public class CreateThread {

    public static void main(String[] args) {
        /* For autonumbering anonymous threads. */
        // 启动时 jvm保证只有1个
//        private static int threadInitNumber;
//        private static synchronized int nextThreadNum() {
//            return threadInitNumber++;
//        }
//        1.创建线程对象, 默认有一个线程名, 以Thread-开头 从0开始计数
//        构造 Thread();
//        Thread-0
//        Thread-1
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        t2.start();
        //t0
        System.out.println(t1.getName());
        //t1
        System.out.println(t2.getName());

//        构造2 Thread(Runnable target)
//        如果构造Thread没有传递Runnable 或者没有复写 Thread的run方法, 则 do nothing
//        如果传递Runnable或者复写run 则会执行该方法的逻辑单元(run 逻辑代码)
        Thread t3 = new Thread((Runnable) null);
        t3.start();
        //t2
//        System.out.println(t3.getName());

        //构造3 Thread(String name)
        Thread t4 = new Thread("myName");
        t4.start();
        System.out.println(t4.getName());
        Thread t5 = new Thread();
        //t3
        System.out.println(t5.getName());

        //构造4 Thread(Runnable target, String name)
        Thread t6 = new Thread(() -> {
            System.out.println("Runnable..." + Thread.currentThread().getName());
        }, "Runnable");
        t6.start();
    }
}
