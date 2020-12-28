package com.zbl.arithmetic.basics.class01;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 15:34 2020/6/2
 * @Description: 插入排序 斗地主? 麻将
 * @Version: $
 */
public class Code03_InsertionSort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 55, 7, 10, 10, 10, 10, 9};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr ));
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // i = j 会有问题 O(N^2)
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Test
    public void test01() throws Exception{
        System.out.println(Math.random());
    }

    // log2N   N = 2 ^ ?  计算机中  默认以2为底


}
