package com.zbl.msb.concurrent.threalLocal;

import java.time.LocalDateTime;
import java.util.Date;

public class Code04_ThreadLocal {


    public static void main(String[] args) {

        final ThreadLocal<String> t = new ThreadLocal<>();
        final ThreadLocal<String> t1 = new ThreadLocal<>();
        final ThreadLocal<String> t2 = new ThreadLocal<>();
        final ThreadLocal<String> t3 = new ThreadLocal<>();
        final ThreadLocal<String> t4 = new ThreadLocal<>();
        final ThreadLocal<String> t5 = new InheritableThreadLocal<>();
        t.set("aaa");
        t1.set("bbbb");
        //A
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(Thread.currentThread().getName() + "  ");
                System.out.println(t.get());
                t.set("aaabbbbb");
                System.out.println(t.get());
            }
        }).start();

        //B
        new Thread(new Runnable() {
            @Override
            public void run() {
                //t.set("sddsddss");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(Thread.currentThread().getName() + "  ");
                System.out.println(t.get());
            }
        }).start();

        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();

    }
}
