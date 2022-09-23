package com.zbl.demo;

import com.zbl.wwj.concurrent.step2.p71.CountDown;

/**
 * @Author: zbl
 * @Date: Created in 2022/7/21
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        long st = System.currentTimeMillis();
        CountDown countDown = new CountDown(3);
        Runnable r = () -> {
            long start = System.currentTimeMillis();
            long res = 0;
            for (int i = 0; i < 98989898; i++) {
                res += i;
            }
            long end = System.currentTimeMillis();
            System.out.printf("Thread:[%s], used:[%s]ms, result:[%s]%n", Thread.currentThread().getName(), (end - start), res);
            countDown.down();
        };
        for (int i = 0; i < 3; i++) {
            new Thread(r).start();
        }
        countDown.await();
        long ed = System.currentTimeMillis();
        System.out.printf("Thread:[%s], total used:[%s]ms", Thread.currentThread().getName(), (ed - st));
    }

}
