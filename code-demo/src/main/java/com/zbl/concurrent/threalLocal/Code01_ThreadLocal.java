package com.zbl.concurrent.threalLocal;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: Created in 19:32 2020/6/18
 * @Description:
 * @Version: $
 */
public class Code01_ThreadLocal {

    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get");
            System.out.println(p.name);
            System.out.println("get end");
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("p = ");
            p.name = "lisi";
            System.out.println("p = end");
        }).start();
    }

}

class Person {

    String name = "zhangsan";

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}
