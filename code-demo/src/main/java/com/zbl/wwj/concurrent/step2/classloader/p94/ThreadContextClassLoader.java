package com.zbl.wwj.concurrent.step2.classloader.p94;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/6 15:47
 * @Description:
 * @Version: $
 */
public class ThreadContextClassLoader {

    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

//        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
