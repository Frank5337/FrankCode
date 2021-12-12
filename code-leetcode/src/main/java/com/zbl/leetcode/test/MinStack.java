package com.zbl.leetcode.test;

import java.util.Stack;

class MinStack {

    Stack<Integer> a;
    Stack<Integer> b;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        a = new Stack();
        b = new Stack();
    }

    public void push(int x) {
        a.push(x);
        if (!b.isEmpty()) {
            if (b.peek() > x) {
                b.push(x);
            }
        } else {
            b.push(x);
        }
    }

    public void pop() {
        Integer pop = a.pop();
        if (!b.isEmpty() && pop.equals(b.peek())) {
            b.pop();
        }
    }

    public int top() {
        return a.peek();
    }

    public int min() {
        if (!b.isEmpty()) {
            return b.peek();
        }
        return 0;
    }

    /**
     * ["MinStack","push","push","push","push","min","pop","min","pop","min","pop","min"]
     * [[],[2],[0],[3],[0],[],[],[],[],[],[],[]]
     *
     * @throws Exception
     */
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */