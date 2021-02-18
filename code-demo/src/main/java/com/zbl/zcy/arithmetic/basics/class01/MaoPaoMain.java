package com.zbl.zcy.arithmetic.basics.class01;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 14:15 2020/5/28
 * @Description:
 * @Version: $
 */
public class MaoPaoMain {

    public static void maoPao(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int temp;
        // 0 + 1 ~ N
        // 0 + 2 ~ N
        // 0 + 3 ~ N
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
//                    temp = arr[j];
//                    arr[j] = arr[i];
//                    arr[i] = temp;
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 8, 6, 7, 4, 9, 1};
        maoPao(arr);
        System.out.println(Arrays.toString(arr));
    }

//    @Test
//    public void test01() throws Exception{
//        Long l = 1L << 55;
//        System.out.println(l);
//        int s = 1 << 2;
//        System.out.println(s);
//    }

}
