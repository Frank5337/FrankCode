package com.zbl.wwj.concurrent.step1.p20;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/5 12:56
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        final SynchronizedRunnable window = new SynchronizedRunnable();
        Thread w1 = new Thread(window, "一号窗口");
        Thread w2 = new Thread(window, "二号窗口");
        Thread w3 = new Thread(window, "三号窗口");

        w1.start();
        w2.start();
        w3.start();
    }
}
