package com.zbl.test.question;

/**
 * @Author: zbl
 * @Date: Created in 18:50 2020/7/29
 * @Description:
 * @Version: $
 */
public class T03_00_cas {

    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) throws InterruptedException {
        //要求用线程交替打印A1B2C3.....Z26
        char[] aI = "ABCDEFG".toCharArray();
        char[] aC = "1234567".toCharArray();


        new Thread(() -> {
            for (char c : aC) {
                while (r != ReadyToRun.T1) {}
                System.out.print(c);
                r = ReadyToRun.T2;

            }

        }, "t1").start();


        new Thread(() -> {
            for (char c : aI) {
                while (r != ReadyToRun.T2){}
                System.out.print(c);
                r = ReadyToRun.T1;

            }
        }, "t2").start();


    }
}
