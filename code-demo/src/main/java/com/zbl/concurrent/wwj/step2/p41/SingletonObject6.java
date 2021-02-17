package com.zbl.concurrent.wwj.step2.p41;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/16
 * @Description:
 * @Version: $
 *
 * 内部类的方式
 * static class
 * static final instance
 * JVM保证单例, 使用时才初始化  jvm确保static只有一份
 * 加载链接解析初始化
 */
public class SingletonObject6 {

    private SingletonObject6() {
    }

    private static class InstanceHolder {
        private static final SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getObject() {
        return InstanceHolder.instance;
    }

}
