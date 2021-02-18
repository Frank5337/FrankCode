package com.zbl.zcy.arithmetic.basics.class03;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/30
 * @Description:
 * @Version: $
 */
public class QuickSortV1 {

    @Test
    public void test01() throws Exception {
        int[] arr = new int[]{8, 1, 3, 2, 7, 4, 5, 1, 32, 55, 66, 5};
        System.out.println(partition(arr, 0, arr.length - 1));
        System.out.println(Arrays.toString(arr));
    }


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return R;
        }
        if (L == R) {
            return R;
        }
        int less = L - 1;
        int index = L;
        int more = R;
        int num = arr[R];
        while (index < more) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, R, more);
        return more;
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //L..R partition arr[R]   [     <= arr[R]   == arr[R]   >= arr[R]     ]
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 1, 3, 2, 7, 4, 5, 1, 32, 55, 66, 5};
        quickSort1(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test02() throws Exception {
        int[] arr = new int[]{32, 55, 66, 7, 8};
        quickSort1(arr);
        System.out.println(Arrays.toString(arr));
    }


}
