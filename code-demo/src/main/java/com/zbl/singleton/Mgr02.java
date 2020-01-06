package com.zbl.singleton;

/**
 * @Author: zbl
 * @Date: Created in 20:40 2019/12/16
 * @Description: 跟01是一个意思
 * @Version: $
 */
public class Mgr02 {

    private static final Mgr02 INSTANCE;

    private Mgr02() {

    }

    /**
     * 静态代码块实现
     */
    static {
        INSTANCE = new Mgr02();
    }

    public static Mgr02 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1);
        System.out.println(m2);
    }
}
