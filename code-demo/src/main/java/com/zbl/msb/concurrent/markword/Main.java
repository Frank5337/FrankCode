package com.zbl.msb.concurrent.markword;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 11:51 2020/4/3
 * @Description: 查看对象头
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        Object obj = new Object();
        ClassLayout layout = ClassLayout.parseClass(obj.getClass());
        //打印空对象大小
        System.out.println(layout.instanceSize());
        System.out.println(layout.toPrintable());

        synchronized (obj) {
            //加锁之后
            System.out.println("after lock");
            System.out.println(layout.toPrintable());
        }

        //解锁之后
        System.out.println("after re-lock");
        System.out.println(layout.toPrintable());

    }

    private static List list = new ArrayList();
    @Test
    public void test01() throws Exception{
//        System.out.println(4 ^ 6);
        long s = System.currentTimeMillis();
        for (int i = 0; i <1_000_000 ; i++) {
//            m1("1", "2", new ArrayList());
            m1("1", "2");
    }
        System.out.println(System.currentTimeMillis() - s);
    }

    private static void m1(String a, String b){
        System.out.println(a);
        System.out.println(b);
        System.out.println(list);
    }

}
