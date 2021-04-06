package com.zbl.wwj.concurrent.step2.classloader.p93;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/6
 * @Description:
 * @Version: $
 */
public class NameSpace {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = NameSpace.class.getClassLoader();
        Class<?> aClass1 = classLoader.loadClass("java.lang.String");
        Class<?> aClass2 = classLoader.loadClass("java.lang.String");
        //findLoadClass包装了只会被加载一次
        System.out.println(aClass1.hashCode());
        System.out.println(aClass2.hashCode());

        //
    }

}
