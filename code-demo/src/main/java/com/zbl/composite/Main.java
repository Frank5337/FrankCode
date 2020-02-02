package com.zbl.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 17:29 2020/1/18
 * @Description: 组合模式  树状结构  组织架构
 * @Version: $
 */
abstract class Node {
    abstract public void p();
}

class LeafNode extends Node {
    String content;
    public LeafNode(String content) {
        this.content = content;
    }
    @Override
    public void p() {
        System.out.println(content);
    }
}

class BranchNode extends Node {
    List<Node> nodes = new ArrayList<>();

    private String name;

    public BranchNode(String name) {
        this.name = name;
    }

    @Override
    public void p() {
        System.out.println(name);
    }

    public Node add(Node node) {
        nodes.add(node);
        return node;
    }
}




public class Main {
    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode chapter1 = new BranchNode("chapter1");
        BranchNode chapter2 = new BranchNode("chapter2");
        Node c11 = new LeafNode("c11");
        Node c12 = new LeafNode("c12");
        BranchNode b21 = new BranchNode("b21");
        Node c211 = new LeafNode("c211");
        Node c212 = new LeafNode("c212");

        root.add(chapter1);
        root.add(chapter2);
        chapter1.add(c11);
        chapter1.add(c12);
        chapter2.add(b21);
        b21.add(c211);
        b21.add(c212);
        String c = "";
        tree(root, c);
    }

    static void tree(Node b, String c) {
        System.out.print(c);
        c +="-";
        b.p();
        if (b instanceof BranchNode) {
            for (Node n : ((BranchNode)b).nodes) {
                tree(n, c);
            }
        }
    }
}
