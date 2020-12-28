package com.zbl.leetcode.f1q21;

/**
 * @Author: zbl
 * @Date: Created in 10:50 2020/10/8
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

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur!=null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * @param head
     * @return
     */
    public ListNode reverseList4(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode cur = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

}
