package com.zbl.zcy.arithmetic.basics.class01;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/9
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class Code07_SelectionSort {


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7,1,3,5,1,6,8,1,3,5,7,5,6};
        printArray(arr);
        selectSort(arr);
        printArray(arr);
    }

    private static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ n-1
        // 1 ~ n-1
        // 2 ~ n-1
        // 3 ~ n-1
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            swap(arr, i, minValueIndex);

        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
