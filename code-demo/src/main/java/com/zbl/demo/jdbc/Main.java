package com.zbl.demo.jdbc;

import org.junit.Test;

/**
 * @Author: frank
 * @Date: Created in 2022/8/11 18:00
 * @Description:
 * @Version: $
 */
public class Main {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void print() {
            ListNode tmp = this;
            while (tmp != null) {
                System.out.print(tmp.val + " ");
                tmp = tmp.next;
            }
            System.out.println("=======================");
        }
    }

    ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode curr = head;
            ListNode pre = null;

            while (curr != null) {
                ListNode tmp = curr.next;
                curr.next = pre;
                pre = curr;
                curr = tmp;
            }
            return pre;
        }
    }

    @Test
    public void test01() throws Exception {
        ListNode res = new Solution().reverseList(listNode);
        res.print();
    }

    @Test
    public void test02() throws Exception {
        listNode.print();
    }


}
