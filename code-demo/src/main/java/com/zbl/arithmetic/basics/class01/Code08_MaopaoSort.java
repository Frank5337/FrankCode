package com.zbl.arithmetic.basics.class01;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/9
 * @Email: zbl5337@gmail.com
 * @Description: 冒泡每一次都是相邻的, 选择可能是跳着的
 */
public class Code08_MaopaoSort {


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ n-3
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    swap(arr, i, j);
                }
            }

        }

    }

    private static void bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ n-3
        int N = arr.length;
        for (int end = N - 1; end >= 0; end--) {
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }

        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
