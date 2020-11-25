package com.zbl.test.jvm.classInittest;

/**
 * @Author: zbl
 * @Date: Create in 2020/10/31
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class Parent {

    static {
        System.out.println(123333);
    }

    public static int A = 1;

    {
        System.out.println("A代码块");
    }

    static {
        System.out.println(A);
        A = 2;
        System.out.println("父静");
    }



}
