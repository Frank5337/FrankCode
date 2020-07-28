package com.zbl.test.question;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: zbl
 * @Date: Created in 19:21 2020/7/28
 * @Description:
 * @Version: $
 */
public class T02_00_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        //要求用线程交替打印A1B2C3.....Z26
        char[] aI = "ABCDEFG".toCharArray();
        char[] aC = "1234567".toCharArray();

        t1 = new Thread(() ->{
            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2);//叫醒T2
                LockSupport.park();//阻塞T1
            }
        }, "t1");

        t2 = new Thread(() ->{
            for (char c : aC) {
                System.out.print(c);
                LockSupport.unpark(t1);//叫醒T1
                LockSupport.park();//阻塞T2
            }
        }, "t2");
        t1.start();
        t2.start();
        System.out.println();
    }
}
