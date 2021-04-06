package com.zbl.wwj.concurrent.step2.classloader.p88;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/5
 * @Description:
 * @Version: $
 */

/**
 * 1.类加载器的委托是优先给父加载器先去尝试加载
 * 2.父加载器和子加载器其实是一种包装关系, 或者包含关系
 * class加载完后会在堆区保持一份
 * 两个不同的classLoader去加载 就会出现不同的( 即 有2个 hashcode也不同
 */
public class MyClassLoaderTest2 {

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        MyClassLoader classLoader2 = new MyClassLoader("MyClassLoader-2");
        classLoader2.setDir("D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\java\\com\\zbl\\wwj\\concurrent\\step2\\classloader\\p88\\app\\classloader2");
        Class<?> klass = classLoader1.loadClass("com.zbl.wwj.concurrent.step2.classloader.p88.MyObject");
        Class<?> klass2 = classLoader2.loadClass("com.zbl.wwj.concurrent.step2.classloader.p88.MyObject");
        System.out.println(klass.hashCode());//1229416514
        System.out.println(klass2.hashCode());//2016447921
        System.out.println(klass.getClassLoader());//com.zbl.wwj.concurrent.step2.classloader.p88.MyClassLoader@4d7e1886
        System.out.println(klass2.getClassLoader());//com.zbl.wwj.concurrent.step2.classloader.p88.MyClassLoader@2f0e140b
        System.out.println(((MyClassLoader)klass.getClassLoader()).getClassLoaderName());
        System.out.println(((MyClassLoader)klass2.getClassLoader()).getClassLoaderName());

    }

}
