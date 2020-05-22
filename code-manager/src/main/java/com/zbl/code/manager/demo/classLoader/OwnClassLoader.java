package com.zbl.code.manager.demo.classLoader;

/**
 * @Author: zbl
 * @Date: 1:48 2020/5/23
 * @Description:
 */
public class OwnClassLoader extends ClassLoader {

    public Object loadRemoteClass(byte[] bytes) throws Exception{
        Class<?> c = super.defineClass(bytes, 0, bytes.length);//定义class
        return c.newInstance();//创建实例
    }
}
