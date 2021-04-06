package com.zbl.wwj.concurrent.step2.classloader.p87;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */
public class BootClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("---------------------------------");
        System.out.println(System.getProperty("java.ext.dirs"));
        //windows下面是分号隔开,  linux下面是:隔开
        Class<?> klass = Class.forName("com.zbl.wwj.concurrent.step2.classloader.p87.SimpleObject");
        System.out.println(klass.getClassLoader());
        System.out.println(klass.getClassLoader().getParent());
        System.out.println(klass.getClassLoader().getParent().getParent());

    }

}
