package com.zbl.arithmetic.class01;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 14:37 2020/6/16
 * @Description:
 * @Version: $
 */
public class Code06_EvenTimesOddTimes {

    //arr 中 只有一种树, 出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 1, 2, 2, 2, 3};
        printOddTimesNum1(arr);
    }

    @Test
    public void test01() throws Exception {
        int n = 10;
        //取反 + 1 求n的最右边的1
        System.out.println(n & ((~n) + 1));
    }

    //一个数组中有两种数出现了奇数次, 其他数都出现了偶数次, 怎么找到并打印这种数
    @Test
    public void test02() throws Exception {
        int[] arr = {1, 1, 1, 2, 2, 2, 3, 3, 4, 4};
        printOddTimesNum12(arr);
    }

    private static void printOddTimesNum12(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^=arr[i];
        }
        // eor = a ^ b
        // eor != 0
        // eor 必然有一个位置上是1
        int rightOne = eor & (~eor + 1); //提取出最右的1
        int onlyOne = 0; //eor'
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + "  " + (eor ^ onlyOne));
    }

}
