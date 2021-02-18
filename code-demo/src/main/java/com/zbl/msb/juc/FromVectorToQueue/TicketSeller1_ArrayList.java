package com.zbl.msb.juc.FromVectorToQueue;

import java.util.ArrayList;
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
 *
 * 索引越界, 超量销售
 */
public class TicketSeller1_ArrayList {

    private static List<String> tickets = new ArrayList<>();

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
