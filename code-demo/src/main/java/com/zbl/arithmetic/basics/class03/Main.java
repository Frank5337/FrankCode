package com.zbl.arithmetic.basics.class03;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/26 11:13
 * @Description: 归并排序与随机快排
 * @Version: $ https://ke.qq.com/webcourse/2145184/102247399#taid=8487538278841248&vid=5285890801537749129
 */
public class Main {

    @Test
    public void test01() throws Exception {
        int[] arr = new int[]{8, 1, 3, 2, 7, 4, 5,1,32,55,66,5};
        int num = 4;
//        System.out.println(Arrays.toString(arr));
        partition(arr, 0, 5);
//        System.out.println(Arrays.toString(partition(arr, 0, 5)));
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 荷兰国旗第一版
     * @param arr
     * @param L
     * @param num
     * @return
     */
    private static int[] partition(int[] arr, int L, int num) {
//        if (L > R) {
//            return new int[]{-1, -1};
//        }
//        if (L == R) {
//            return new int[]{L, R};
//        }
        int less = L - 1;
        int more = arr.length - 1;
        int index = L;
        while (index < more) {
            if (arr[index] == 5) {
                index++;
            } else if (arr[index] < 5) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, more--);
            }

        }
        System.out.println(Arrays.toString(arr));
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}


