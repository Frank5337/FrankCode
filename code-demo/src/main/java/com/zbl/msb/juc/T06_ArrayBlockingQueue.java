package com.zbl.msb.juc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 18:20 2020/7/26
 * @Description: offer 有返回值可以判断添加成功还是失败, 还有另外一个方法可以指定时间尝试往里面加1秒钟, 1秒后进不去就返回了
 * peek  看头部的. 不取出
 * pool  看头部的, 并且取出
 * put   添加, 满了阻塞
 * add   添加, 满了抛异常
 * take  取出, 没有的话阻塞
 *
 */
public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException{
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        //满了阻塞
        //strs.put("aaa");

        //满了抛异常
        //strs.add("aaa");

        //添加失败返回false
        boolean o1 = strs.offer("aaa");
        System.out.println("offer: " + o1);
        boolean o2 = strs.offer("aaa", 1, TimeUnit.SECONDS);
        System.out.println("offer one seconds: " + o2);
    }

}
