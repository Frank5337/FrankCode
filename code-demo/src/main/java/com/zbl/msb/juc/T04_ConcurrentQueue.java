package com.zbl.msb.juc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: zbl
 * @Date: 17:33 2020/7/26
 * @Description:
 */
public class T04_ConcurrentQueue {

    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            //add操作, 成功返回true
            strs.offer("a" + i);
        }

        System.out.println(strs);

        System.out.println(strs.size());

        //取出来并且remove掉  pollFirst  索引0  第一个先进去的, 类比队列的 先进先出
        System.out.println("--- pool ---");
        System.out.println(strs.poll());
        System.out.println(strs.size());

        //只取出来, 不remove  peekFirst
        System.out.println("--- peek ---");
        System.out.println(strs.peek());
        System.out.println(strs.size());
    }
}
