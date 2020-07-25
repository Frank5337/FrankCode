package com.zbl.juc.FromVectorToQueue;

import java.util.List;
import java.util.Vector;

/**
 * @Author: zbl
 * @Date: Created in 16:22 2020/7/25
 * @Description:
 * @Version: $
 * <p>
 * 有N张火车票, 每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题 ?
 * 重复销售 ?  超量销售 ?
 *
 * 现在来看看最早的容器, Vector, 内部是自带锁de, 很多方法都是二话不说先synchronized
 * 但是还是不对, 锁是为了线程的安全, 但是就当我们调用size方法的时候加锁了, 调用remove方法的时候加锁了, 但是没有再中间加锁
 * 所以是两步操作, 不是原子性的, 所以又超卖了
 *
 * 9997,  重复销售了
 */
public class TicketSeller2_Vector {

    private static List<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10_000; i++) {
            tickets.add("票编号: " + i);
        }
    }

    private static int times = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    //从数组中删除指定的元素, 并返回此元素
                    System.out.println("销售了--" + tickets.remove(0));
                    times += 1;
                    System.out.println(times);
                }
            }).start();
        }

    }
}
