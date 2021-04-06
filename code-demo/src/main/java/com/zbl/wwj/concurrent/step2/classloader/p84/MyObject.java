package com.zbl.wwj.concurrent.step2.classloader.p84;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class MyObject {

    public static int x = 10;

    static {
        System.out.println(x);
        x += 100;
        System.out.println(x);
        y = 100;
//        System.out.println(y);
    }

    public static int y = 10;
}
