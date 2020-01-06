package com.zbl.singleton;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 10:01 2019/12/17
 * @Description:
 * @Version: $
 * java的创始人 在《Effective java》中写了一种单例的方法
 * <p>
 * 不仅可以解决线程同步, 还可以防止反序列化
 *
 * 做单例的时候, 要防止反序列化,
 * java的反射 通过class文件, 把整个class load到内存 然后再new实例出来,
 * 01-07都可以找到class文件 new Instance 通过反序列化方法, new 一个实例出来
 * 实际当中要防止反序列化很复杂
 *
 * 枚举类 不会被反序列化的原因 是因为: 枚举类没有构造方法 (java规定,  反编译后 枚举是一个abstract class)
 *
 */
public enum Mgr08 {

    INSTANCE;

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(() -> {
            System.out.println(Mgr08.INSTANCE.hashCode());
        }).start());
    }

}
