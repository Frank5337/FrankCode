package com.zbl.concurrent.wwj.step1.p13;

import org.junit.Test;

import java.util.Optional;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/31
 * @Description:
 * @Version: $
 */
public class ThreadSimpleAPI {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t.start();

        Optional.of("Name: " + t.getName()).ifPresent(System.out::println);
        Optional.of("Id  : " + t.getId()).ifPresent(System.out::println);
        //优先级, 企图改变顺序
        Optional.of("P   : " + t.getPriority()).ifPresent(System.out::println);
    }

    @Test
    public void test01() throws Exception {
        String test = "null";
        Optional.of(test).ifPresent(System.out::println);
    }
}
