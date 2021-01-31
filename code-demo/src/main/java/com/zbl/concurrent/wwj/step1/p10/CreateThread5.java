package com.zbl.concurrent.wwj.step1.p10;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/24
 * @Description:
 * @Version: $
 */
public class CreateThread5 {

    private static int counter;

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            counter++;
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("Total created thread nums <=" + counter);
    }


}
