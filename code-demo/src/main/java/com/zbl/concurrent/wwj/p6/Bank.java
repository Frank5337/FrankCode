package com.zbl.concurrent.wwj.p6;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/23
 * @Description:
 * @Version: $ https://www.bilibili.com/video/BV1hJ411D7k2?p=6
 */
public class Bank {

    public static void main(String[] args) {
        //runnable 可执行, 为了将可执行的逻辑单元与线程控制分离开来
        //与策略模式比较接近?
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();

        Thread t1 = new Thread(ticketWindow, "一号窗口");
        Thread t2 = new Thread(ticketWindow, "二号窗口");
        Thread t3 = new Thread(ticketWindow, "三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
