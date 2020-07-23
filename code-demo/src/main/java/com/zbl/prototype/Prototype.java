package com.zbl.prototype;

import org.junit.Test;

import java.util.HashSet;

/**
 * @Author: zbl
 * @Date: Created in 14:52 2020/2/25
 * @Description: Prototype原型   浅克隆 和 深克隆
 *               引用类型需要深克隆 String类型 不需要深克隆
 *               String类型指向的是常量池  String类型 是改引用
 *
 *               java自带prototype模式
 *               实现原型模式需要实现标记型接口CloneAble
 *               一般重写clone()方法
 *                  · 如果只重写, 没实现接口 调用时会报异常
 *               一般对于一个对象已确定, 需要产生很多相同对象的时候
 *               需要区分深克隆与浅克隆
 * @Version: $
 */
public class Prototype {

    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person();
        Person p2 = (Person) p1.clone();
        System.out.println(p2.age + " " + p2.score);
        System.out.println(p2.lc);
        System.out.println(p1.lc == p2.lc);
        System.out.println(p1.lc.street.equals(p2.lc.street));

        p1.lc.street = "hz";
        System.out.println("p1: " + p1.lc);
        System.out.println("p2: " + p2.lc);
    }

    @Test
    public void test01() throws Exception{
        int[] arr = {1,2,3};
        double[] arr2 = {1.1,2.2,3.3};
        for (int i = 0; i < arr.length ; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("--------------------");
        for (int i = 0; i < arr.length ; ++i) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test02() throws Exception{
        HashSet h1 = new HashSet();
        HashSet h2 = new HashSet();
        h1.removeAll(h2);
    }

}

class Person implements Cloneable {
    int age = 8;
    int score = 100;

    Location lc = new Location("sh" ,22);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //浅克隆
        //return super.clone();

        //深克隆
        Person p = (Person) super.clone();
        p.lc = (Location) p.lc.clone();
        return p;
    }
}

class Location implements Cloneable {
    String street;
    Integer num;

    public Location(String street, Integer num) {
        this.street = street;
        this.num = num;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", num=" + num +
                '}';
    }
}


