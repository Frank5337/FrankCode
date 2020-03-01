package com.zbl.test;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: 19:17 2019/12/29
 * @Description:
 */
public class DemoTest02 {

    public static void main(String[] args) {
        int[] arr = {1,4,3,9,5};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void sort(int[] arr) {
        int temp;
        for (int i = 0; i <arr.length ; i++) {
            for (int j = i + 1; j <arr.length ; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
