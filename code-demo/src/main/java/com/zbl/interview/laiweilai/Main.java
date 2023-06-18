package com.zbl.interview.laiweilai;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Frank
 * @Date: Created in 2023/5/5
 * @Description:
 * @Version: $
 */
public class Main {

    static class Node {
        private int id;

        private int parentId;

        private String name;

        public Node(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getParentId() {
            return parentId;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        listNode.add(new Node(1, 0, "AA"));
        listNode.add(new Node(2, 1, "BB"));
        listNode.add(new Node(3, 2, "CC"));
        listNode.add(new Node(4, 2, "DD"));
        listNode.add(new Node(5, 3, "EE"));
        listNode.add(new Node(6, 3, "FF"));
        listNode.add(new Node(7, 3, "GG"));
        listNode.add(new Node(8, 2, "HH"));
        listNode.add(new Node(9, 5, "II"));
        listNode.add(new Node(10, 5, "JJ"));
        listNode.add(new Node(11, 8, "KK"));
        listNode.add(new Node(12, 8, "LL"));
        listNode.add(new Node(13, 8, "MM"));
        listNode.add(new Node(14, 9, "NN"));
        listNode.add(new Node(15, 9, "OO"));
        listNode.add(new Node(16, 10, "PP"));
        listNode.add(new Node(17, 11, "QQ"));
        listNode.add(new Node(18, 11, "RR"));
        listNode.add(new Node(19, 18, "SS"));
        listNode.add(new Node(20, 18, "TT"));
        print();
    }

    private static Map<Integer, Node> m = new HashMap<>();

    private static final List<Node> listNode = new ArrayList<>();

    private static void print() {
        Map<Integer, List<Node>> map = Main.listNode.stream().collect(Collectors.groupingBy(Node::getParentId));
        m = Main.listNode.stream().collect(Collectors.toMap(Node::getId, Function.identity()));
        List<Integer> nothing = new ArrayList<>();
        doPrint2(map, 0, nothing);
    }

    private static List<Node> getSub(int parentId) {
        Map<Integer, List<Node>> map = listNode.stream().collect(Collectors.groupingBy(Node::getParentId));
        return map.get(parentId) == null ? new ArrayList<>() : map.get(parentId);
    }

    private static void doPrint(List<Node> list, int deep, List<Integer> nothing) {
        deep++;
        for (Node node : list) {
            System.out.println(getDeepStr(deep) + node.getName());
            doPrint(getSub(node.getId()), deep, nothing);
            nothing.add(node.getId());
        }
    }

    private static void doPrint2(Map<Integer, List<Node>> map, int deep, List<Integer> nothing) {
        Set<Map.Entry<Integer, List<Node>>> entries = map.entrySet();
        for (Map.Entry<Integer, List<Node>> entry : entries) {
            if (!nothing.contains(entry.getKey())) {
                if (m.get(entry.getKey()) == null) {
                    continue;
                }
                System.out.println(getDeepStr(deep) + m.get(entry.getKey()).getName());
                doPrint(entry.getValue(), deep, nothing);
                nothing.add(entry.getKey());
            }
        }
    }


    private static String getDeepStr(int deep) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < deep; i++) {
            res.append("  ");
        }
        return res.toString();
    }

}
