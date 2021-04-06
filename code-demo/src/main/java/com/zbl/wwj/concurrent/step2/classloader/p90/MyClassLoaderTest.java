package com.zbl.wwj.concurrent.step2.classloader.p90;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        DecryptClassLoader decryptClassLoader = new DecryptClassLoader();
//        MyClassLoader decryptClassLoader = new MyClassLoader();
        Class<?> aClass = decryptClassLoader.loadClass("com.zbl.wwj.concurrent.step2.classloader.p88.MyObject");
        System.out.println(aClass);
        Object newInstance = aClass.newInstance();
        Method hello = aClass.getMethod("hello");
        Object result = hello.invoke(newInstance);
        System.out.println(result);

    }
}
