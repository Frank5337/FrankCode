package com.zbl.tank;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 17:29 2020/7/10
 * @Description:
 * @Version: $
 */
public class Demo01 {

    public static void main(String[] args) {
        int[] arr = {};
        List list = Arrays.asList(arr);
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o);
        }
    }

}
