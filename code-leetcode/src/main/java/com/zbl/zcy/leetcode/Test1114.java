package com.zbl.zcy.leetcode;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: zbl
 * @Date: Create in 2020/10/17
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class Test1114 {

    public static void main(String[] args) throws InterruptedException {
        Test1114 test1114 = new Test1114();
        test1114.first( () -> System.out.println("first"));
        test1114.second( () -> System.out.println("second"));
        test1114.third( () -> System.out.println("third"));
    }

    CountDownLatch s = new CountDownLatch(1);
    CountDownLatch t = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        s.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        s.await();
        printSecond.run();
        t.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        t.await();
        printThird.run();
    }
}
