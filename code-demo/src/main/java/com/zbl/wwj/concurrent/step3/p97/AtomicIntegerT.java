package com.zbl.wwj.concurrent.step3.p97;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/7 11:24
 * @Description:
 * @Version: $
 */
public class AtomicIntegerT {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);
        //最快失败策略
        boolean b = i.compareAndSet(12,20);
        System.out.println(b);
        System.out.println(i.get());
    }
}
