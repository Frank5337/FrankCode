package com.zbl.zcy.leetcode.f1q21;

import org.junit.Test;

import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/29
 * @Description:
 * @Version: $
 */
public class Reversal {

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        print(node);
        System.out.println();
        print(reversal(node));
    }

    public static ListNode reversal(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        ListNode tmp = null;
        while (curr != null) {
            //记录下一个节点的位置
            tmp = curr.next;
            //指针翻转, 指向前面null
            curr.next = pre;
            //pre进1位
            pre = curr;
            //curr进1位
            curr = tmp;

        }
        return pre;
    }

    @Test
    public void test01() throws Exception {
        String[] ips = new String[]{};
        String a = null;
        a.split(",");
        for (String ip : ips) {

        }
        System.out.println(123);

    }

    @Test
    public void test02() throws Exception {
        tee(null);
    }

    public void tee(List<String> stringList) {
        for (String s : stringList) {
            System.out.println(s);
        }
    }
}
