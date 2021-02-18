package com.zbl.zcy.arithmetic.basics.class03;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/1 9:33
 * @Description:
 * @Version: $
 */
public class Test01 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 6, 3, 5, 6, 8, 12, 4, 77, 787, 34};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l + (int) (Math.random() * (r - l) + 1), r);
        int[] M = partition(arr, l, r);
        process(arr, l, M[0] - 1);
        process(arr, M[1] + 1, r);
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[] {-1, -1};
        }
        if (l == r) {
            return new int[] {l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        int num = arr[r];
        while (index < more) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

}
