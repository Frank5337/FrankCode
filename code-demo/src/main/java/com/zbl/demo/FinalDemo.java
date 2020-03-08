package com.zbl.demo;

/**
 * @Author: zbl
 * @Date: 1:13 2020/3/8
 * @Description:
 */
public class FinalDemo {

    public static void main(String[] args) {
        m1(10);
    }

    public static void m1(final Integer integer) {
        //integer = 1;
        System.out.println(integer);
    }
}
