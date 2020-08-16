package com.zbl.test.question;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: zbl
 * @Date: Created in 18:50 2020/7/29
 * @Description:
 * @Version: $
 */
@SuppressWarnings("all")
public class T04_00_ArrayBlockingQueue {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) throws InterruptedException {
        //要求用线程交替打印A1B2C3.....Z26
        char[] aI = "ABCDEFG".toCharArray();
        char[] aC = "1234567".toCharArray();


        new Thread(() -> {
            for (char c : aC) {
                try {
                    q1.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
                try {
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }, "t1").start();


        new Thread(() -> {
            for (char c : aI) {
                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t2").start();


    }
}
