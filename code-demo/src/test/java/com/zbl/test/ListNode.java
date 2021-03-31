package com.zbl.test;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/26 10:39
 * @Description:
 * @Version: $
 */
public class ListNode {
    public int val;
    public ListNode next;



    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
