package com.zbl.wwj.concurrent.step3.p174_Lock_Free;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/15 8:42
 * @Description:  非线程安全的队列
 * @Version: $
 */
public class MyQueue<T> {

    private static class Node<T> {

        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return element == null? "" : element.toString();
        }
    }

    private Node<T> first,last;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peekFirst () {
        return first == null ? null : first.getElement();
    }

    public T peekLast () {
        return last == null ? null : last.getElement();
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<>(element, null);
        if (size == 0) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T res = first.getElement();
            first = first.getNext();
            size--;
            if (size == 0) {
                last = null;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();
        queue.addLast("Hello");
        queue.addLast("World");
        queue.addLast("Java");
        //FIFO
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());

    }

}
