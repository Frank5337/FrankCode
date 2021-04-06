package com.zbl.wwj.concurrent.step2.classloader;

import java.util.Random;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/2 16:11
 * @Description:
 * @Version: $
 */
public class ClassActiveUse {

    public static void main(String[] args) {
        System.out.println(Obj.salary);                                    //不会初始化, 会放到常量池
//        System.out.println(Obj.x);                                       //会初始化 编译的时候不会算出来, 运行的时候才会算出来
//        System.out.println(Obj.s);
    }
}

class Obj {

    public static final long salary = 100000L;

    public static String s = "ceshi";

    public static final int x = new Random().nextInt();

    static {
        System.out.println("Obj 被初始化");
    }
}
