package com.zbl.concurrent.wwj.p9;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/24
 * @Description:
 * @Version: $
 */
public class CreateThread3 {

    //方法区
    private int i = 0;

    //引用地址在方法区,  具体值在堆
    private byte[] bytes = new byte[1024];

    private static int counter = 0;

    //jvm will create a thread named "name"
    public static void main(String[] args) {
        //create a jvm stack

        //局部变量表
//        int j = 0;
        //局部变量表,   数据在堆
//        int[] arr = new int[1024];
        try {
            add(0);
        } catch (Error e) {
            //虚拟机栈不够 栈溢出.StackOverflowError 23535
            e.printStackTrace();
            System.out.println(counter);
        }
    }

    private static void add(int i) {
        ++counter;
        add(i + 1);
    }

}
