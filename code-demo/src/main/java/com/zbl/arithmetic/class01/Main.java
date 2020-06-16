package com.zbl.arithmetic.class01;

/**
 * @Author: zbl
 * @Date: Created in 11:25 2020/6/15
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        int a = 6;
        int b = 6;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }
}
