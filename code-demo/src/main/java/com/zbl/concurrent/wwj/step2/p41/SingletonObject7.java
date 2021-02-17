package com.zbl.concurrent.wwj.step2.p41;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/16
 * @Description:
 * @Version: $
 * <p>
 * 枚举单例
 */
public class SingletonObject7 {

    private SingletonObject7() {
    }

    private enum Singleton {

        INSTANCE;

        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }

    public static SingletonObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 1000).forEach(i -> new Thread(String.valueOf(i)) {
            @Override
            public void run() {
                System.out.println(getInstance());
            }
        }.start());
    }


}
