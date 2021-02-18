package com.zbl.zcy.arithmetic.basics.class01;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/14
 * @Description:
 * @Version: $
 */
public class Sort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubble(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i - 1 ; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void select(int[] arr) {
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    public static void insert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >=0 && arr[j] > arr[j + 1] ; j--) {
                swap(arr, j, j+1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 7, 8, 4, 4, 6, 9, 7};
        insert(arr);
        System.out.println(Arrays.toString(arr));
    }
}
