package com.zbl.msb.juc;

import java.util.PriorityQueue;

/**
 * @Author: zbl
 * @Date: 19:23 2020/7/26
 * @Description:
 */
public class T07_01_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");
        q.add("f");

        for (int i = 0; i < 6; i++) {
            System.out.println(q.poll());
        }
    }
}
