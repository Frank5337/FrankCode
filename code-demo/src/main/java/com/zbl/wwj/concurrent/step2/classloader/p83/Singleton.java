package com.zbl.wwj.concurrent.step2.classloader.p83;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    public static int x = 0;

    public static int y;


    /**
     * 1,1
     * 准备阶段
     * int x = 0;
     * int y = 0;
     * instance = null
     */

    /**
     * instance = null
     * x = 0
     * y = 0
     *
     * instance = new Singleton();
     * x++ => x=1
     * y++ => y=1
     *
     * x = 0;
     * y = 1;
     */

    private Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
