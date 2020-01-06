package com.zbl.singleton;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 9:59 2019/12/19
 * @Description:
 * @Version: $
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类, 这样可以实现懒加载
 * 完美写法之一  无Mgr01的问题
 *
 * 实现了懒加载, 也保证只有一个实例
 *
 * 虚拟机加载class时候, 保证只加载1次 so, 只有一个Mgr07, 只有一个Mgr07Holder 只有一个Instance
 */
public class Mgr07 {

    private Mgr07() {
    }

    /**
     * Mgr07被加载的时候, 里面的Mgr07Holder是不会被加载的
     */
    private static class Mgr07Holder {
        /**
         * final 和 static 前后顺序无关 在class中只是两个字段标记的域
         */
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        //第一次調的时候才会加载
        return Mgr07Holder.INSTANCE;
    }

    //同一个类的不同对象, hash码是不同的
    //此种方法没问题
    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i -> new Thread(() -> {
            System.out.println(getInstance().hashCode());
        }).start());
    }
}
