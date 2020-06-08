package com.zbl.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: zbl
 * @Date: Created in 10:06 2020/6/8
 * @Description: 信号灯, 用来限制多个线程访问同一个资源,
 *               与锁不同的是, 锁只允许线程访问一个特定的资源, 而信号灯是允许指定数量的线程访问同一个资源,
 *               可以用来协调多线程,
 *
 *               Semaphore 并不是java语言特有的, 几乎所有的并发语言, 信号灯的模型都一样
 *               由 计数器, 等待队列, 和三个方法组成(init , up, down)
 *               计数器: 记录还可以有多少个线程来访问资源
 *               等待队列: 将等待资源的线程放入此队列
 * @Version: $
 */
public class TestSemaphore {

    private static ExecutorService executorService = Executors.newFixedThreadPool(6);
    //            /ˈseməfɔː(r)/
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            executorService.execute(
                () -> {
                try {
                    //获得
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "-----");
                    for(;;) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }

            });
        }
    }
}
