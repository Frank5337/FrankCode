package com.zbl.wwj.concurrent.step1.p22;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/6
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        new Thread(SynchronizedStatic::m1).start();
        new Thread(SynchronizedStatic::m2).start();
        new Thread(SynchronizedStatic::m3).start();
    }
}
