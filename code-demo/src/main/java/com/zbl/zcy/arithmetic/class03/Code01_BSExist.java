package com.zbl.zcy.arithmetic.class03;

public class Code01_BSExist {

    //arr保证有序
    public static boolean find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int min = 0;
        while (L < R) {
            min = L + (R - L) >> 1;
            if (arr[min] == num) {
                return true;
            } else if (arr[min] > num) {
                R = min - 1;
            } else {
                L = min + 1;
            }
        }
        return false;
    }
}
