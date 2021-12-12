package com.zbl.wwj.concurrent.step3.p154_collections;

/**
 * @Author: zbl
 * @Date: Created in 2021/11/16 16:05
 * @Description:
 * @Version: $
 */
public class PriorityLinkedList<E extends Comparable<E>> {

    private PriorityLinkedList.Node<E> first;

    private final PriorityLinkedList.Node<E> NULL = (PriorityLinkedList.Node<E>) null;

    private static final String PLAIN_NULL = "null";

    private int size;

    public PriorityLinkedList() {
        this.first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E... elements) {
        final PriorityLinkedList<E> list = new PriorityLinkedList<E>();
        if (elements.length > 0) {
            for (E e : elements) {
                list.addFirst(e);
            }
        }
        return list;

    }

    public PriorityLinkedList<E> addFirst(E e) {
        final PriorityLinkedList.Node<E> newNode = new PriorityLinkedList.Node<>(e);
        Node<E> pre = NULL;
        Node<E> curr = first;
        while (curr != null && e.compareTo(curr.value) > 0) {
            pre = curr;
            curr = pre.next;

        }
        if (pre == NULL) {
            this.first = newNode;
        } else {
            pre.next = newNode;
        }
        newNode.next = curr;
        this.size++;
        return this;
    }

    public boolean contains(E e) {
        PriorityLinkedList.Node<E> current = first;
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
            throw new PriorityLinkedList.NoElementException("The linked list is empty. ");
        }
        PriorityLinkedList.Node<E> node = first;
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
            PriorityLinkedList.Node<E> curr = first;
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
        PriorityLinkedList.Node<E> next;

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
//        PriorityLinkedList<String> list = PriorityLinkedList.of("Hello", "Java", "Python", "C++");
        PriorityLinkedList<Integer> list = PriorityLinkedList.of(1, 21, 33, 4);
//        list.addFirst("Country").addFirst("Test");
        System.out.println(list.size());
        System.out.println(list.contains(5));
        System.out.println(list.toString());

        while (!list.isEmpty()) {
            System.out.println(list.removeFirst());
        }
        System.out.println(list.size());
        System.out.println(list.isEmpty());
    }

}
