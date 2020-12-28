package com.zbl.test;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/7
 * @Description:
 * @Version: $
 */
public class DemoTest09 {

    public static void main(String[] args) {
        Object o = new Object();
        synchronized (o) {
            System.out.println(123);
        }
    }

}
