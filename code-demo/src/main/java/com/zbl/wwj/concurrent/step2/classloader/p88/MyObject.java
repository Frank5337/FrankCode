package com.zbl.wwj.concurrent.step2.classloader.p88;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class MyObject {

    static {
        System.out.println("My object static block");
    }

    public String hello() {
        return "Hello world cls";
    }
}
