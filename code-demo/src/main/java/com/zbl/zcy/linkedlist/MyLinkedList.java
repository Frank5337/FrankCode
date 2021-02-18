package com.zbl.zcy.linkedlist;

/**
 * @Author: zbl
 * @Date: Created in 18:29 2020/4/3
 * @Description:
 * @Version: $
 */
public class MyLinkedList<T> {
    //下一个节点
    private MyLinkedList<T> next;
    //上一个节点
    private MyLinkedList<T> pre;

    private T e;

    public MyLinkedList(MyLinkedList<T> next, MyLinkedList<T> pre, T e) {
        this.next = next;
        this.pre = pre;
        this.e = e;
    }

    /**
     * 删除节点
     */
    public void remove() {
        next.pre = pre;
        pre.next = next;
    }

    /**
     * 修改节点
     */
    public void set(T e) {
       MyLinkedList<T> link = new MyLinkedList<>(next, pre, e);
       next.pre = link;
       pre.next = link;
    }
}
