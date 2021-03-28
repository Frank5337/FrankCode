package com.zbl.wwj.concurrent.step2.p66;

import com.zbl.zcy.leetcode.f1q21.ListNode;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/27
 * @Description:
 * @Version: $
 */
public class ContextTest {

    public static void main(String[] args) {
//        IntStream.range(0, 5).forEach(i -> {
//            new Thread(new ExecutionTask()).start();
//        });
        System.out.println(2 % 5);
        System.out.println(5 - 2 % 5 -1);

        System.out.println(5 % 2);
        System.out.println(2 - 5 % 2 -1);
        ListNode head = new ListNode();
        ListNode temp = head, newHead = new ListNode();
        System.out.println(temp);
        System.out.println(head);
        System.out.println(newHead);
    }
}
