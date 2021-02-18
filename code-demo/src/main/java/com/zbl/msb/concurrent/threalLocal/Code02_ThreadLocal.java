package com.zbl.msb.concurrent.threalLocal;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 19:32 2020/6/18
 * @Description:
 * @Version: $
 */
public class Code02_ThreadLocal {

    //volatile static Person p = new Person();

    private static ThreadLocal<Person> t1 = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get");
            System.out.println(t1.get());
            System.out.println("get end");

            t1.set(new Person("test"));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t1.get().name);
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("set");
            t1.set(new Person());
            System.out.println("set end");
        }).start();
    }

}
