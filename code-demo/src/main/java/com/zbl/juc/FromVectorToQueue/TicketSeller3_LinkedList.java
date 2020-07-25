package com.zbl.juc.FromVectorToQueue;

import java.util.LinkedList;
import java.util.List;

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
 * <p>
 * 虽然用了这个加锁的容器, 但是调用这个并发容器的时候, 你是调用了两个原子方法,
 * 所以在外层还得再加一把锁, 继续判断size, 售出去不断的remove,
 * 这是没有问题的,  但是它不是效率最高的方案
 */
public class TicketSeller3_LinkedList {

    private static final List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10_000; i++) {
            tickets.add("票编号: " + i);
        }
    }

    private static int times = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0) break;
                        //从数组中删除指定的元素, 并返回此元素
                        System.out.println("销售了--" + tickets.remove(0));
                        times += 1;
                        System.out.println(times);

                    }

                }
            }).start();
        }

    }
}
