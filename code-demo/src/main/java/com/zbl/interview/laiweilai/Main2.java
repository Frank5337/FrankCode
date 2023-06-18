package com.zbl.interview.laiweilai;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Frank
 * @Date: Created in 2023/5/5
 * @Description:
 * @Version: $
 */
public class Main2 {

    static class Node {
        int id;
        int parentId;
        String name;

        public Node(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }
    }

    static class NewNode {
        Node node;
        int level;
        List<NewNode> subNodeList;

        public NewNode(Node node) {
            this.node = node;
            this.level = 0;
            this.subNodeList = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<Node> nodeList = Arrays.asList(
//                new Node(1, 0, "AA"),
//                new Node(2, 1, "BB"),
//                new Node(3, 1, "CC"),
//                new Node(4, 3, "DD"),
//                new Node(5, 3, "EE"),
//                new Node(6, 2, "FF"),
//                new Node(7, 2, "GG"),
//                new Node(8, 4, "HH"),
//                new Node(9, 5, "II"),
//                new Node(10, 0, "JJ"),
//                new Node(11, 10, "KK"),
//                new Node(12, 10, "LL"),
//                new Node(13, 12, "MM"),
//                new Node(14, 13, "NN"),
//                new Node(15, 14, "OO")
                new Node(1, 0, "AA"),
                new Node(2, 1, "BB"),
                new Node(3, 2, "CC"),
                new Node(4, 2, "DD"),
                new Node(5, 3, "EE"),
                new Node(6, 3, "FF"),
                new Node(7, 3, "GG"),
                new Node(8, 2, "HH"),
                new Node(9, 5, "II"),
                new Node(10, 5, "JJ"),
                new Node(11, 8, "KK"),
                new Node(12, 8, "LL"),
                new Node(13, 8, "MM"),
                new Node(14, 9, "NN"),
                new Node(15, 9, "OO"),
                new Node(16, 10, "PP"),
                new Node(17, 11, "QQ"),
                new Node(18, 11, "RR"),
                new Node(19, 18, "SS"),
                new Node(20, 18, "TT")
        );
        print(nodeList);
    }

    /**
     * 处理数组中的节点数据
     *
     * @param nodeList
     */
    private static List<NewNode> handleList(List<Node> nodeList) {
        List<NewNode> newNodeList = new ArrayList<>();
        for (Node node : nodeList) {
            // 判断父节点
            if (node.parentId == 0) {
                NewNode newNode = new NewNode(node);
                newNodeList.add(newNode);
            } else {
                addNewNode(node, newNodeList);
            }
        }
        return newNodeList;
    }

    /**
     * 循环加入节点
     *
     * @param node
     * @param newNodeList
     */
    static void addNewNode(Node node, List<NewNode> newNodeList) {
        for (NewNode newNode : newNodeList) {
            Node node1 = newNode.node;
            List<NewNode> subNodeList = newNode.subNodeList;
            if (node1.id == node.parentId) {
                // 新的节点是当前节点的子节点
                NewNode subNewNode = new NewNode(node);
                subNewNode.level = newNode.level + 1;
                subNodeList.add(subNewNode);
            } else {
                addNewNode(node, subNodeList);
            }
        }
    }


    static void printNewNode(List<NewNode> newNodeList) {
        if (CollectionUtils.isEmpty(newNodeList)) {
            return;
        }
        for (NewNode newNode : newNodeList) {
            int level = newNode.level;
            System.out.println(space(level) + newNode.node.name);
            printNewNode(newNode.subNodeList);
        }
    }

    public static void print(List<Node> nodeList) {
        List<NewNode> newNodeList = handleList(nodeList);
        printNewNode(newNodeList);
    }

    /**
     * 拼接空格循环的数量
     **/
    public static String space(int tableSpace) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tableSpace; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

}