package com.zbl.arithmetic.basics.class01;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 11:39 2020/5/28
 * @Description: 选择排序
 * @Version: $
 */
public class selectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            //找到最小值的位置放前面
            //其实就是索引为i的和数字最小的换位置
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
            System.out.println(Arrays.toString(arr));
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,4,3,2,0};
        selectionSort(arr);
//        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test01() throws Exception {
        //a = b, b = a
        int a = 1;
        int b = 2;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }


}
