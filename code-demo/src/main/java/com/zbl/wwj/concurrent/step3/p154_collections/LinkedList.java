package com.zbl.wwj.concurrent.step3.p154_collections;

/**
 * @Author: zbl
 * @Date: Created in 2021/11/16 15:23
 * @Description:
 * @Version: $
 */
public class LinkedList<E> {

    private Node<E> first;

    private final Node<E> NULL = (Node<E>) null;

    private static final String PLAIN_NULL = "null";

    private int size;

    public LinkedList() {
        this.first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static <E> LinkedList<E> of(E... elements) {
        final LinkedList<E> list = new LinkedList<>();
        if (elements.length > 0) {
            for (E e : elements) {
                list.addFirst(e);
            }
        }
        return list;

    }

    public LinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        newNode.next = first;
        this.size++;
        this.first = newNode;
        return this;
    }

    public boolean contains(E e) {
        Node<E> current = first;
        while (current != null) {
            if (current.value == e) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static class NoElementException extends RuntimeException {
        public NoElementException(String message) {
            super(message);
        }
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            throw new NoElementException("The linked list is empty. ");
        }
        Node<E> node = first;
        first = node.next;
        size--;
        return node.value;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        } else {
            StringBuilder builder = new StringBuilder("{");
            Node<E> curr = first;
            while (curr != null) {
                builder.append(curr.toString()).append(",");
                curr = curr.next;
            }
            builder.replace(builder.length() - 1, builder.length(), "}");
            return builder.toString();
        }

    }


    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (null != value) {
                return value.toString();
            }
            return "null";
        }
    }

    public static void main(String[] args) {
        LinkedList<String> list = LinkedList.of("Hello", "Java", "Python", "C++");
        list.addFirst("Country").addFirst("Test");
        System.out.println(list.size());
        System.out.println(list.contains("Java"));
        System.out.println(list.toString());

        while (!list.isEmpty()) {
            System.out.println(list.removeFirst());
        }
        System.out.println(list.size());
        System.out.println(list.isEmpty());
    }


}
