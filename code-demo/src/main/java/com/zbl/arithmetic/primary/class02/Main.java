package com.zbl.arithmetic.primary.class02;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/17
 * @Description:
 * @Version: $
 */
public class Main {

    //给定一个 1 ~ 5 的随机函数 求20 ~ 56随机函数
    private int f() {
        // [0~1)
        //Math.random()
        return (int) (Math.random() * 5 + 1);
    }

    /**
     * 0 1 发生器
     * @return
     */
    private int f2() {
        int ans;
        do {
            ans = f();
        } while (ans == 3);
        return ans > 3 ? 0 : 1;
    }

    /**
     * 0 ~ 36 等概率
     * 32,16,8,4,2,1
     * @return
     */
    private int f3() {
        int ans = (f2() << 5) + (f2() << 4) + (f2() << 3) + (f2() << 2) + (f2() << 1) + (f2() << 0);
        while (ans > 36) {
            ans = f3();
        }
        return ans;
    }


    private void test(int len, int times) {
        int[] arr = new int[len];
        for (int i = 0; i < times; i++) {
            int ans = f();
            arr[ans]++;
        }
        printArr(arr);
    }

    private void test2(int len, int times) {
        int[] arr = new int[len];
        for (int i = 0; i < times; i++) {
            int ans = f2();
            arr[ans]++;
        }
        printArr(arr);
    }

    private void test3(int len, int times) {
        int[] arr = new int[len];
        for (int i = 0; i < times; i++) {
            int ans = f3();
            arr[ans]++;
        }
        printArr(arr);
    }

    private void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "这个数, 出现了" + arr[i] + " 次");
        }
    }

    @Test
    public void test01() throws Exception {
        //test(6, 10000000);
        //test2(2, 10000000);
        test3(35, 10000000);
    }
}
