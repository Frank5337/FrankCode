package com.zbl.concurrent.wwj.p10;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/24
 * @Description:
 * @Version: $
 */
public class CreateThread5 {

    private static int counter;

    public static void main(String[] args) {
        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                new Thread(() -> {
                    counter++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Error e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }


}
