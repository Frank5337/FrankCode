package com.zbl.msb.designpatterns.singleton;

/**
 * @Author: zbl
 * @Date: Created in 20:26 2019/12/16
 * @Description: 单例模式 04 05 多线程有问题,   01最实用, 06双重检测 07 JVM保证单例 08 enum实现单例
 * @Version: $
 */
public class Main {
    public static void main(String[] args) {
        Mgr01 mgr01 = Mgr01.getInstance();
        System.out.println(mgr01);
    }
}
