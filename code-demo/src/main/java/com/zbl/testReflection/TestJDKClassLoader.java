package com.zbl.testReflection;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 11:50 2020/1/21
 * @Description:
 * @Version: $
 */
public class TestJDKClassLoader {

    /**最早启动类 最核心的ClassLoader
     * Bootstrap class loader 最顶层的ClassLoader
     * 负责专门管理一些class,最核心的类是 被Bootstrap load进来的 C/汇编/C++写的
     *
     * extesion classloader JDK内置的,   扩展ClassLoader  负责jdk的扩展类
     * JDK/jre/lib/ext 这个下面的jar包, 是由
     *
     * @param args
     */
    public static void main(String[] args) {
        //Bootstrap ClassLoader
        //System.out.println(String.class.getClassLoader());
        //extesion
        //System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        //System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        //最核心的 把其他的ClassLoader loade 进来
        //application APPClassLoader
        //System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());
        //系统的 ClassLoader 就是 appClassLoader
        //System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
        ClassLoader c = TestJDKClassLoader.class.getClassLoader();
        while (c != null) {
            System.out.println(c.getClass().getName());
            c = c.getParent();
        }

    }

    @Test
    public void test01() throws Exception{
        System.out.print("Java\n");
    }

}
