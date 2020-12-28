package com.zbl.arithmetic.basics.class01;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/9
 * @Email: zbl5337@gmail.com
 * @Description: 冒泡每一次都是相邻的, 选择可能是跳着的
 */
public class Code09_InsertionSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
        printArray(arr);
        insertSort(arr);
        printArray(arr);
    }

    private static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //0-1
        //0-2
        //0-3
        //0-N
//        for (int i = 1; i < arr.length; i++) {
//            // j = i-1 证明左边有数字
//            // arr[j] > arr[j + 1] 并且左边的数比右边大,  那就要交换
//            for (int j = i-1; j >=0 && arr[j] > arr[j + 1]; j--) {
//                swap(arr, j, j + 1);
//            }
//        }
       //for (int i = 1; i < arr.length; i++) {
//            // j = i-1 证明左边有数字
//            // arr[j] > arr[j + 1] 并且左边的数比右边大,  那就要交换
//            int newNumIndex = i;
//            while (newNumIndex - 1 >= 0 && arr[newNumIndex -1] > arr[newNumIndex]) {
//                swap(arr, newNumIndex - 1, newNumIndex);
//                newNumIndex--;
//            }
//        }
        //冒泡
//        for (int i = arr.length - 1; i >= 0; i--) {
//            for (int j = 0; j < i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    swap(arr, j, j + 1);
//                }
//            }
//        }
        //选择
//        for (int i = 0; i < arr.length - 1; i++) {
//            int min = i;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[min] > arr[j]) {
//                    min = j;
//                }
//            }
//            swap(arr, i, min);
//        }
//
//      插入?
        for (int i = 1; i < arr.length; i++) {
            // j = i-1 证明左边有数字
            // arr[j] > arr[j + 1] 并且左边的数比右边大,  那就要交换
            for (int j = i - 1; j >=0 && arr[j] > arr[j+1] ; j--) {
                swap(arr, j, j + 1);
            }
        }

    }

}
