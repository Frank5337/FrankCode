package com.zbl.concurrent.wwj.step1.p7;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/23
 * @Description:
 * @Version: $
 */
public class Bank {

    private final static int MAX = 50;

    private static int index = 1;

    public static void main(String[] args) {
        //runnable 可执行, 为了将可执行的逻辑单元与线程控制分离开来
        //与策略模式比较接近?
        final Runnable ticketWindow = () -> {

            while (index <= MAX) {
                System.out.println(Thread.currentThread() + "的号码是: " + (index++));
            }
        };

        Thread t1 = new Thread(ticketWindow, "一号窗口");
        Thread t2 = new Thread(ticketWindow, "二号窗口");
        Thread t3 = new Thread(ticketWindow, "三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
