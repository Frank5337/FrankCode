package com.zbl.wwj.concurrent.step2.classloader.p84;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class Parent {

    public static int A = 1;

    static {
        A = 2;
    }
}
