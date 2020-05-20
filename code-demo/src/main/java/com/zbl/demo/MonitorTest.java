package com.zbl.demo;

import org.junit.Test;

import java.util.Scanner;

/**
 * @Author: zbl
 * @Date: Created in 11:14 2020/4/26
 * @Description:
 * @Version: $
 */
public class MonitorTest {

    static final Object o = new Object();

    public static void main(String[] args) {
//        synchronized (o) {
//            System.out.println(123);
//        }
        //m1();
    }

    public static synchronized void m1(){
        System.out.println("m1");
    }

    @Test
    public void test01() throws Exception{
        Scanner keyIn = new Scanner(System.in);
        System.out.println("How old are you ? ");
        String age = keyIn.next();

        System.out.println("What's your name ? ");
        String name = keyIn.nextLine();

        System.out.println("Hello " + name + ", you are " + age + " years old !");


    }

    @Test
    public void test02() throws Exception{
        Zbling zbling = new Zbling();
        zbling.start();
        for (;;) {
            if (zbling.isFlag()) {
                System.out.println("main=" + Thread.currentThread().getId());
                System.out.println("有点东西");
                break;
            }
        }
    }


static class Zbling extends Thread {
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + flag);
        System.out.println("run=" + Thread.currentThread().getId());
    }
}



}
