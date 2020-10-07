package com.zbl.test;

/**
 * @Author: zbl
 * @Date: Created in 2020/9/25
 * @Description:
 * @Version: $
 */
public class DemoTest07 {

    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("a");
                    if (i == 5) {
                        try {
                            System.out.println("-------------------------------------");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("b");
                }
            }
        }).start();

    }
}
