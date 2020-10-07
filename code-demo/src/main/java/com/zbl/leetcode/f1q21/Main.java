package com.zbl.leetcode.f1q21;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 10:50 2020/5/1
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.print("\n初始: ");
        sout(listNode1);
        ListNode newNode1 = reverseList(listNode1);
        System.out.print("\nreverseList: ");
        sout(newNode1);
        ListNode newNode2 = reverseList2(newNode1);
        System.out.print("\nreverseList2: ");
        sout(newNode2);
        System.out.println();

    }

    private static void sout(ListNode listNode) {
        System.out.print(listNode.val + " ");
        if (listNode.next != null) {
            sout(listNode.next);
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next;
        ListNode newNode = reverseList(head.next);
        temp.next = head;
        head.next = null;
        return newNode;
    }

    public static ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        while (l1.next != null) {
            while (l2.next != null) {
                if (l1.val > l2.val) {

                }
            }
        }
        return null;
    }

}
