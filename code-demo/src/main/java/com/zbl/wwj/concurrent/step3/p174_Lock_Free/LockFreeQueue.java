package com.zbl.wwj.concurrent.step3.p174_Lock_Free;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/15 8:56
 * @Description:
 * @Version: $
 */
public class LockFreeQueue<E> {

    private AtomicReference<Node<E>> head, tail;

    private AtomicInteger size = new AtomicInteger(0);

    public LockFreeQueue() {
        Node<E> node = new Node<>(null);
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException("The null element not allow");
        }
        Node<E> newNode = new Node<>(e);
        Node<E> tailNode = tail.getAndSet(newNode);
        tailNode.next = newNode;
        size.incrementAndGet();

    }

    public E removeFirst() {
        Node<E> headNode, valueNode;
        do {
            headNode = head.get();
            valueNode = headNode.next;
            //head改成value, 不成功 循环
        } while (valueNode != null && !head.compareAndSet(headNode, valueNode));

        E value = valueNode != null ? valueNode.element : null;
        if (valueNode != null) {
            valueNode.element = null;
        }
        size.decrementAndGet();
        return value;
    }

    private static class Node<E> {

        E element;
        volatile Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element == null ? "" : element.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap<Long, Object> data = new ConcurrentHashMap<>();
        final LockFreeQueue<Long> queue = new LockFreeQueue<>();
        AtomicInteger counter = new AtomicInteger(0);
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            while (counter.getAndIncrement() <= 20) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.addLast(System.currentTimeMillis());
            }
        }).forEach(service::submit);

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            //太快的话 5个while执行完 线程就执行完了 die了
            while (counter.getAndDecrement() > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long value = queue.removeFirst();
                System.out.println(value);
                data.put(value, new Object());
            }
        }).forEach(service::submit);

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println(data.size());
        System.out.println("=========");
        System.out.println(data.keys());
    }

    private boolean isEmpty() {
        return size.get() == 0;
    }

    @Test
    public void test01() throws Exception {
        final LockFreeQueue<Long> queue = new LockFreeQueue();
        System.out.println(queue.removeFirst());
        queue.addLast(System.currentTimeMillis());
        System.out.println(queue.removeFirst());
    }

}
