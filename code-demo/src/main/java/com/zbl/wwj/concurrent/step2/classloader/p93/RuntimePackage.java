package com.zbl.wwj.concurrent.step2.classloader.p93;

import com.zbl.wwj.concurrent.step2.classloader.p92.SimpleClassLoader;
import com.zbl.wwj.concurrent.step2.classloader.p92.SimpleClassLoaderTest;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/6
 * @Description:
 * @Version: $
 */
public class RuntimePackage {

    //RuntimePackage
    //com.wwj.current
    //Boot.Ext.App.com.wwj.current
    //Boot.Ext.App.com.wwj.current.SimpleObj
    //Boot.Ext.App.com.wwj.current.SimpleClassLoader  这里是看不到下面加载了什么东西的
    //Boot.Ext.App.SimpleClassLoader.com.wwj.current.SimpleClassLoaderTest

    //父类加载器看不到子类加载器加载的类
    //不同命名空间下的类加载器之间互相不可访问
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> clazz = simpleClassLoader.loadClass("com.zbl.wwj.concurrent.step2.classloader.p92.SimpleClassLoaderTest");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());
        SimpleClassLoaderTest s = (SimpleClassLoaderTest) clazz.newInstance();
        //会出现xxx不能转成xxx  两个一模一样
        //runtimePackage是由系统加载器去做的
        //simple是子类加载器
        //父亲是看不到子类的加载的
        //两个不同的类加载器之间都是看不到的    jvm  instanceof

        //com.zbl.wwj.concurrent.step2.classloader.p92.SimpleClassLoaderTest cannot be cast to
        //com.zbl.wwj.concurrent.step2.classloader.p92.SimpleClassLoaderTest
    }
}
