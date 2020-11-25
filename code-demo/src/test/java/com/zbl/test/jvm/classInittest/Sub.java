package com.zbl.test.jvm.classInittest;

/**
 * @Author: zbl
 * @Date: Create in 2020/10/31
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class Sub extends Parent {

    public static int B = A;

    {
        System.out.println("B代码块");
    }

    static {
        System.out.println("子静");
    }
}
