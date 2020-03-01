package com.zbl.test;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @Author: zbl
 * @Date: 16:35 2019/12/29
 * @Description:
 */
public class DemoTest01 {

    private static LinkedList<Integer> list = new LinkedList();

    public static void main(String[] args) {
        for (Integer integer : list) {

        }
        list.forEach( l ->{

        });

        method(1);
        method(5);
        method(7);
        method(6);
        System.out.println(list);
    }
    public static void method(int e) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > e) {
                list.add(i ,e);
                return;
            }
        }
        list.add(e);

    }

}
