package com.zbl.strategy;

/**
 * @Author: zbl
 * @Date: Created in 16:58 2019/12/23
 * @Description:
 * @Version: $
 */
public class Cat implements Comparable<Cat> {
    int weight, height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

//    public int compareTo(Cat cat) {
//        if (this.weight < cat.weight) return -1;
//        else if (this.weight > cat.weight) return 1;
//        else return 0;
//    }

    public static void main(String[] args) {
        String abc = "abc";
        System.out.println(abc.contains("a"));
    }

    @Override
    public int compareTo(Cat cat) {
        if (this.weight < cat.weight) return -1;
        else if (this.weight > cat.weight) return 1;
        else return 0;

    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
