package com.zbl.zcy.arithmetic.basics.class01;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 11:25 2020/6/15
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
//        int a = 6;
//        int b = 6;
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//        System.out.println(a);
//        System.out.println(b);
        int[] arrB = {1, 3, 2, 4, 7, 8, 4, 4, 6, 9, 7};
        System.out.println(Arrays.toString(arrB));
        bubbleSort(arrB);
        System.out.println(Arrays.toString(arrB));
        System.out.println();
        int[] arrS = {1, 3, 2, 4, 7, 8, 4, 4, 6, 9, 7};
        System.out.println(Arrays.toString(arrS));
        selectSort(arrS);
        System.out.println(Arrays.toString(arrS));
        System.out.println();
        int[] arrI = {1, 3, 2, 4, 7, 8, 4, 4, 6, 9, 7};
        System.out.println(Arrays.toString(arrI));
        insertSort(arrI);
        System.out.println(Arrays.toString(arrI));
        System.out.println();

    }

    //冒泡
    private static void bubbleSort(int[] arrB) {
        if (arrB == null || arrB.length < 2) {
            return;
        }
        //0~n-1
        //0~n-2
        //0~n-3
        for (int i = arrB.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arrB[j] > arrB[j + 1]) {
                    swap(arrB, j, j + 1);
                }
            }

        }
    }

    //选择
    private static void selectSort(int[] arrS) {
        if (arrS == null || arrS.length < 2) {
            return;
        }
        //0~n-1
        //1~n-1
        //2~n-1
        for (int i = 0; i < arrS.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arrS.length; j++) {
                min = arrS[j] < arrS[min] ? j : min;
            }
            swap(arrS, i, min);
        }
    }

    private static void insertSort(int[] arrI) {
        if (arrI == null || arrI.length < 2) {
            return;
        }
        // 0~0有序
        // 0~1有序
        // 0~i有序
        // 从后面往前面比 小的放前面
        for (int i = 1; i < arrI.length; i++) {
            for (int j = i - 1; j >= 0 && arrI[j] > arrI[j + 1]; j--) {
                swap(arrI, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
