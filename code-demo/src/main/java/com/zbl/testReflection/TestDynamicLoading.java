package com.zbl.testReflection;

/**
 * @Author: zbl
 * @Date: Created in 11:32 2020/1/21
 * @Description: 动态加载  ClassLoader 是用到的时候才加载
 *               static在加载后执行一次
 *               dynamic语句块 每次new新的对象都会执行
 *                  等同于构造方法中的语句
 *                  用的较少
 * @Version: $
 */
public class TestDynamicLoading {
    // -verbore:class
    public static void main(String[] args) {
        new A();
        System.out.println("*-------------------------------*");
        new B();

        new C();
        new C();

        new D();
        new D();
    }

}

class A {

}

class B {

}
class C {
    //只加载一次
    static {
        System.out.println("CCCCC");
    }

}

class D {
    //动态语句块, 每次new出来的时候 会调用一次
    //相当于 这段代码, 无条件的加到了加在了每一个构造方法的前面 .
    {
        System.out.println("DDDDD");
    }
}
