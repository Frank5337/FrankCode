package com.zbl.msb.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Author: zbl
 * @Date: 19:25 2020/7/26
 * @Description: SynchronousQueue初始容量为0,
 * 这个东西 不是用来装内容的,SynchronousQueue是专门用来两个线程之间传内容的
 * 给线程下达任务的,
 * <p>
 * 看下面的代码
 * 有一个线程起来等着take, 里面没有值一定take不到, 然后就等着
 * 然后当put的时候能取出来, take到了之后能打印出来, 最后打印这个容器的size一定是0
 * 打印出aaa这个没问题,
 * 当把线程注释掉, add就会报错, 原因是满了, 容器为0, 不可以往里面扔东西
 * <p>
 * 只能用来阻塞式put调用,
 * 要求是前面得有人拿着这个东西的时候才能往里面装,
 * 但容量为0
 * 说白了就是要递到一个人手里才行,
 * SynchronousQueue在线程池里面的用处特别大, 很多线程取任务, 互相之间任务调度就是它
 */
public class T08_SynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println("take ---");
                System.out.println(strs.take());
                System.out.println("take over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " put ---");
        strs.put("aaa");//阻塞等待消费者消费
        //strs.put("bbb");

        //会报错 java.lang.IllegalStateException: Queue full
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}
