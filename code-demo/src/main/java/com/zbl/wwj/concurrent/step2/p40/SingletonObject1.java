package com.zbl.wwj.concurrent.step2.p40;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/16
 * @Description: 懒汉式
 * @Version: $
 */
public class SingletonObject1 {

    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {

    }

    public static SingletonObject1 getInstance() {
        return instance;
    }
}
