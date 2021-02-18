package com.zbl.wwj.concurrent.step1.p8;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/24
 * @Description:
 * @Version: $
 */
public class CreateThread2 {

    //如果构造线程对象时, 未传入ThreadGroup, Thread会默认获取父线程的ThreadGroup 作为该线程的ThreadGroup
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
//        System.out.println(t.getThreadGroup());
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        //列出
        threadGroup.enumerate(threads);

        //Thread[main,5,main]
        //Thread[Monitor Ctrl-Break,5,main]
        //Thread[Thread-0,5,main]
        for (Thread thread : threads) {
            System.out.println(thread + "----isDaemon: " + thread.isDaemon());
        }
//        Arrays.asList(threads).forEach(System.out::println);

    }
}
