package com.zbl.leetcode.test;

import java.util.Stack;

class CQueue2 {

    Stack<Integer> s1;
    Stack<Integer> s2;

    public CQueue2() {
        //先进后出
        s1 = new Stack();//新增
        s2 = new Stack();//删除
    }
    
    public void appendTail(int value) {
        s1.push(value);
    }
    
    public int deleteHead() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty() && s1.peek() != null) {
                s2.push(s1.pop());
            }
        }
        if (s2.peek() != null) {
            return s2.pop();
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        //先进先出
        CQueue2 cQueue = new CQueue2();
        cQueue.appendTail(1);//1
        cQueue.appendTail(2);//2 - 1
        cQueue.appendTail(3);//3 - 2 - 1
        System.out.println(cQueue.deleteHead());//1
        System.out.println(cQueue.deleteHead());//2
        System.out.println(cQueue.deleteHead());//3
    }
}