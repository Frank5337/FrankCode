package com.zbl.msb.designpatterns.singleton;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 20:41 2019/12/16
 * @Description:
 * @Version: $
 * 懒汉式, lazy loading
 * 虽然达到了按需初始化的目的, 但却带来线程不安全的问题
 * 多线程访问的时候, 会有问题
 */
public class Mgr05 {

    private static Mgr05 INSTANCE;

    private Mgr05() {
    }

    /**
     * static synchronized 锁定的是类 在当前 锁定的是Mgr04.class
     * @return
     */
    public static  Mgr05 getInstance() {
        if (INSTANCE == null) {
            //妄图通过减少同步代码块的方式提高效率,  然后不行
            synchronized (Mgr05.class) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            INSTANCE = new Mgr05();
        }
        return INSTANCE;
    }

    //同一个类的不同对象, hash码是不同的
    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i -> new Thread(() -> {
            System.out.println(getInstance().hashCode());
        }).start());
    }
}
