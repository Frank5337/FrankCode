package com.zbl.wwj.concurrent.step3.p154_collections;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/12
 * @Description:
 * @Version: $
 */
public class SimpleSkipList {

    private final static byte HEAD_NODE = (byte) -1;
    private final static byte DATA_NODE = (byte) 0;
    private final static byte TAIL_NODE = (byte) 1;


    private static class Node {
        private Integer value;
        //上下左右四个节点
        private Node up, down, left, right;
        //标识是头 是尾
        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this.value = value;
        }

    }

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_NODE);
        this.tail = new Node(null, TAIL_NODE);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    /**
     * 从跳表种寻找元素
     *
     * @param element
     * @return
     */
    private Node find(Integer element) {
        //第一层最左边
        Node curr = head;
        for (; ; ) {
            while (curr.right.bit != TAIL_NODE && curr.right.value <= element) {
                curr = curr.right;
            }
            if (curr.down != null) {
                curr = curr.down;
            } else {
                break;
            }
        }
        return curr;  //the element <= current.right (if exists)
    }

    /**
     * nearNode nearNodeR
     * ⬇
     * nearNode newNode nearNodeR
     *
     * @param element
     */
    public void add(Integer element) {
        Node nearNode = this.find(element);
        Node newNode = new Node(element);

        newNode.left = nearNode;
        newNode.right = nearNode.right;

        nearNode.right.left = newNode;
        nearNode.right = newNode;

        int currentLevel = 0;
        while (random.nextDouble() < 0.3d) {

            if (currentLevel >= height) {
                height++;
                //向上移动一层  头 和 尾巴 各提一层
                Node dumyHead = new Node(null, HEAD_NODE);
                Node dumyTail = new Node(null, TAIL_NODE);

                dumyHead.right = dumyTail;
                dumyHead.down = head;
                head.up = dumyHead;

                dumyTail.left = dumyHead;
                dumyTail.down = tail;
                tail.up = dumyTail;

                head = dumyHead;
                tail = dumyTail;

            }

            //提一层
            while (nearNode != null && nearNode.up == null) {
                nearNode = nearNode.left;
            }
            nearNode = nearNode.up;

            //把节点放进去
            Node upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = newNode;

            nearNode.right.left = upNode;
            nearNode.right = upNode;

            //向上提
            newNode.up = upNode;
            newNode = upNode;

            currentLevel++;
        }

        size++;

    }

    public void dumpSkipList() {
        Node temp = head;
        int i = height + 1;
        while (temp != null) {
            System.out.printf("Total [%d] height [%d]", height + 1, i--);
            Node node = temp.right;
            while (node.bit == DATA_NODE) {
                System.out.printf("-> %d ", node.value);
                node = node.right;
            }
            System.out.print("\n");
            temp = temp.down;
        }
        System.out.print("=====================================\n");
    }

    public boolean contains(Integer element) {
        Node node = this.find(element);
        return node.value.equals(element);
    }

    public Node get(Integer element) {
        Node node = this.find(element);
        return node.value.equals(element) ? node : null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SimpleSkipList skipList = new SimpleSkipList();
        skipList.add(10);
        skipList.dumpSkipList();

        skipList.add(1);
        skipList.dumpSkipList();

        Random random = new Random();
        for (int i = 0; i < 18; i++) {
            skipList.add(random.nextInt(1000));
        }
        skipList.dumpSkipList();
    }

}
