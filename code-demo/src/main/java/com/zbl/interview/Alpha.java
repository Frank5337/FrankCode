package com.zbl.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Frank
 * @Date: Created in 2023/5/6
 * @Description:
 * @Version: $
 */
public class Alpha extends Base {

    public static void main(String[] args) {
        String a = "aaaa";
        String b = new String("aaaa");
        Integer c = 100;
        Integer d = 300;
        Integer e= 100;
        Integer f= 300;
        System.out.println(a == "aaaa");
        System.out.println(c == e);
        System.out.println(b == "aaaa");
        System.out.println(d == f);
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService aa = Executors.newScheduledThreadPool(1);
        ExecutorService bb = Executors.newFixedThreadPool(1);
        try {
            ExecutorService cc = Executors.newSingleThreadExecutor(null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
