package com.zbl.wwj.concurrent.step2.classloader.p88;

import java.lang.reflect.Method;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("MyClassLoader");
        Class<?> klass = classLoader.loadClass("com.zbl.wwj.concurrent.step2.classloader.p88.MyObject");
        System.out.println(klass);
        System.out.println(klass.getClassLoader());
        Object o = klass.newInstance();
        Method hello = klass.getMethod("hello", new Class<?>[]{});
        Object result = hello.invoke(o, new Object[]{});
        System.out.println(result);

    }

}
