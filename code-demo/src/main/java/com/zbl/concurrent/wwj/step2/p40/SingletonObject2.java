package com.zbl.concurrent.wwj.step2.p40;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/16
 * @Description: 饿汉式  DCL
 * @Version: $
 *
 * 解决性能问题
 * 产生单个实例
 * 懒加载的方式
 *
 * 不加 volatile 会出现空指针异常
 * volatile 可见性 happens-before 防止指令重排序    (读之前必须有一个写的操作?
 * (JVM优化 指令重排序,  编译优化/运行优化)
 */
public class SingletonObject2 {

    private static volatile SingletonObject2 object;

    private SingletonObject2() {
    }

    public static SingletonObject2 getObject() {
        if (object == null) {
            synchronized (SingletonObject2.class) {
                if (object == null) {
                    object = new SingletonObject2();
                }
            }
        }
        return object;
    }
}
