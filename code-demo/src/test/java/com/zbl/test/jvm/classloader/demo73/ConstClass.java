package com.zbl.test.jvm.classloader.demo73;

/**
 * @Author: zbl
 * @Date: Create in 2020/11/1
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class ConstClass {

    static {
        System.out.println("ConstClass init ! ");
    }

    public static final String HELLOWORLD = "hello world";
}
