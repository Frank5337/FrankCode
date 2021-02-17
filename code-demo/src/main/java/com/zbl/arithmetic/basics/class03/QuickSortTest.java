package com.zbl.arithmetic.basics.class03;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/30
 * @Description:
 * @Version: $
 */
public class QuickSortTest {


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    @Test
    public void test01() throws Exception {
        int[] arr = new int[]{8, 1, 3, 2, 7, 4, 5, 1, 32, 55, 66, 5};
        System.out.println(partition(arr, 0, arr.length - 1));
        System.out.println(Arrays.toString(arr));
    }

    public static int[] partition(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int index = left;
        int less = left - 1;
        int more = right;
        int num = arr[more];
        while (index < more) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, ++less, index++);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int)(Math.random() * (right - left) + 1), right);
        int[] M = partition(arr, left, right);
        process(arr, left, M[0] - 1);
        process(arr, M[1] + 1, right);

    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    @Test
    public void test02() throws Exception {
        int[] arr = new int[]{8, 1, 3, 2, 7, 4, 5, 1, 32, 55, 66, 5};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
