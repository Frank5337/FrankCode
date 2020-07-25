package com.zbl.juc.FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
 * 效率最高的就是Queue
 * 设计主要就是为了高并发, 为了多线程用的, 所以以后多线程单个元素就要考虑Queue了
 * poll 移除头部并返回, 没有就为null
 *
 */
public class TicketSeller4_Queue {

    private static final Queue<String> tickets = new ConcurrentLinkedQueue<>();

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
                    //检索并删除头部
                    String s = tickets.poll();
                    if (s == null) break;
                    System.out.println("销售了--" + s);
                    times += 1;
                    System.out.println(times);
                }
            }).start();
        }

    }
}
