package com.zbl.msb.designpatterns.singleton;

/**
 * @Author: zbl
 * @Date: Created in 20:41 2019/12/16
 * @Description:
 * @Version: $
 * 懒汉式, lazy loading
 * 虽然达到了按需初始化的目的, 但却带来线程不安全的问题
 * 多线程访问的时候, 会有问题
 */
public class Mgr03 {

    private static Mgr03 INSTANCE;

    private Mgr03() {
    }

    ;

    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    //同一个类的不同对象, hash码是不同的
    public static void main(String[] args) {
        /*IntStream.rangeClosed(1,100).forEach(i -> new Thread(() -> {
            System.out.println(getInstance().hashCode());
        }).start());*/
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr03.getInstance().hashCode())
            ).start();
            /*
            324801639
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
            1339159671
             */
        }
    }
}
