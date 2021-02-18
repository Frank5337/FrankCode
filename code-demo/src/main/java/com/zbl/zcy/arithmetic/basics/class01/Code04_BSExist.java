package com.zbl.zcy.arithmetic.basics.class01;

/**
 * @Author: zbl
 * @Date: Created in 16:33 2020/6/2
 * @Description: 二分
 * @Version: $
 */
public class Code04_BSExist {

    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length;
        int mid = 0;
        while (L < R) {
            //mid = (L + R) / 2
            //mid = L + (R - L) / 2
            //mid = L + ((R - L) >> 1)
            //扩展 N * 2 + 1 -->> (N << 1) | 1   向左移是补0  或1 就是 +1
            mid = L+ ((R + L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num;
    }

}
