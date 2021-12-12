package com.zbl.leetcode.test;

import java.util.Stack;

class CQueue {

    Stack<Integer> s1;
    Stack<Integer> s2;

    public CQueue() {
        //先进后出
        s1 = new Stack();//操作的那个
        s2 = new Stack();//转换的那个
    }
    
    public void appendTail(int value) {
        while (!s1.isEmpty() && s1.peek() != null) {
            s2.push(s1.pop());
        }
        s2.push(value);
        while (!s2.isEmpty() && s2.peek() != null) {
            s1.push(s2.pop());
        }
    }
    
    public int deleteHead() {
        if (!s1.isEmpty() && s1.peek() != null) {
            return s1.pop();
        }
        return -1;
    }

    public static void main(String[] args) {
        //先进先出
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);//1
        cQueue.appendTail(2);//2 - 1
        cQueue.appendTail(3);//3 - 2 - 1
        System.out.println(cQueue.deleteHead());//1
        System.out.println(cQueue.deleteHead());//2
        System.out.println(cQueue.deleteHead());//3

    }
}