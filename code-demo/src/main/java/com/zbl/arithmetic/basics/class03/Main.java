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
        int[] arr = new int[]{8,1,3,2,7,4,5};
        int num = 4;
        System.out.println(Arrays.toString(arr));
        partition(arr, num);
        System.out.println(Arrays.toString(arr));
    }

    private void partition(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                swap(arr, i, i-1);
            }
        }
    }

    private void swap(int[] arr, int a, int b ){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}


