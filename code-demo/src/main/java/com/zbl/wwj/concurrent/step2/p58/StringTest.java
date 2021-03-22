package com.zbl.wwj.concurrent.step2.p58;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/21
 * @Description:
 * @Version: $
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = s1.replace("l", "k");
        System.out.println(s2);
        System.out.println(s1.getClass()+ " " + s1.hashCode());
        System.out.println(s2.getClass()+ " " + s2.hashCode());
        //String 不可变
        //StringBuffer  线程安全的    可变的
        //StringBuilder 线程不安全的 可变的
    }
}
