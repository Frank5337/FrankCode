package com.zbl.arithmetic.basics.class03;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/29
 * @Description:
 * @Version: $
 */
public class HLGQ {

    public static void main(String[] args) {
//        int[] arr = {1, 4, 2, 3, 6, 8, 4, 2, 5, 7, 9, 3, 4, 5};
        int[] arr = new int[]{32, 55, 66, 7, 8};
        int num = 8;
        partition(arr, num);
        System.out.println(Arrays.toString(arr));
    }

    private static void partition(int[] arr, int num) {
        //最左边
        int less = -1;
        //最右边
        int more = arr.length;
        //0
        int index = 0;
        while (index < more) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] > num) {
                swap(arr, --more, index);
            } else {
                swap(arr, index++, ++less);
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


}
