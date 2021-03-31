package com.zbl.test;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/26 10:39
 * @Description:
 * @Version: $
 */
public class LeetCodeTest {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3,
                                        new ListNode(3, null)))));

        ListNode newN = deleteDuplicates(listNode);
        print(newN);

    }


    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode next = head.next;
        while (next != null && curr.val == next.val) {
            curr.next = next.next;
            curr = curr.next;
            next = curr.next;
        }
        return head;
    }

    public static void print(ListNode listNode) {
        ListNode newNode = listNode;
        System.out.println(newNode.val);
        while (newNode.next != null) {
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }

}
