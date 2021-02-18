package com.zbl.wwj.concurrent.step1.p12;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/31
 * @Description:
 * @Version: $
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "running");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "stop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };//new

        //main线程生命周期结束之后, 创建的守护线程也结束
        //用处?
        //用守护线程去做心跳,
        // 建立链接线程A活着的情况下,才会心跳, A死掉之后, 心跳也同时关掉
        t.setDaemon(true);
        //runnable 可执行状态,  有可能变成runnable状态, 也有可能dead 也有可能blocked状态
        t.start();
//        t.join();
//        t.join(1000, 1);

        //5
        System.out.println(Thread.currentThread().getPriority());

        System.out.println(Thread.currentThread().getName());
    }
}
