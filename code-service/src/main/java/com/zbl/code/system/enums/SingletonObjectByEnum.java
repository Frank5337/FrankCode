package com.zbl.code.system.enums;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 19:38 2019/12/16
 * @Description:
 * @Version: $
 */
public class SingletonObjectByEnum {

    private SingletonObjectByEnum() {

    }

    //创建内部枚举，利用枚举只会被创建初始化一次的特性来创建单例对象
    private enum Singleton {

        INSTANCE;

        private final SingletonObjectByEnum instace;

        Singleton() {
            instace = new SingletonObjectByEnum();
        }

        private SingletonObjectByEnum getInstance() {
            return instace;
        }
    }

    public static SingletonObjectByEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }


    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(() -> {
            System.out.println(SingletonObjectByEnum.getInstance());
        }).start());
    }

}
