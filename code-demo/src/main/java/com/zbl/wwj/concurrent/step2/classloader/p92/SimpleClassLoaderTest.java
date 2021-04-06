package com.zbl.wwj.concurrent.step2.classloader.p92;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/6
 * @Description:
 * @Version: $
 */
public class SimpleClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        //自己是加载不了的 是自己的父亲加载的 App加载的
        //因为系统加载器可以看得到这个文件  源码在编译的时候会编译进去
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        //爹 爷爷 xxx找不到 才是自己去做 去load
        Class<?> aClass = simpleClassLoader.loadClass("com.zbl.wwj.concurrent.step2.classloader.p92.SimpleObject");
        System.out.println(aClass);//class com.zbl.wwj.concurrent.step2.classloader.p92.SimpleObject
        System.out.println(aClass.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2


        //打破双亲委派之后 simpleClassLoader 重写了 loadClass方法后
//        system: sun.misc.Launcher$AppClassLoader@18b4aac2
//        class com.zbl.wwj.concurrent.step2.classloader.p92.SimpleObject
//        com.zbl.wwj.concurrent.step2.classloader.p92.SimpleClassLoader@4d7e1886

    }

    @Test
    public void test01() throws Exception {
        //注释掉 java开头的包system加载器加载
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> clazz = simpleClassLoader.loadClass("java.lang.String");
        System.out.println(clazz);
        //java.lang.SecurityException: Prohibited package name: java.lang
        //由此可得 即使打破了双亲委派机制 也不允许去加载Java.lang
    }

}
