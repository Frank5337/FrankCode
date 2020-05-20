package com.zbl;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: zbl
 * @Date: Created in 11:21 2020/4/20
 * @Description:
 * @Version: $
 */
public class Solution {

    @Test
    public void test01() throws Exception{
        System.out.println(fib(43));
        System.out.println(fib1(43));
        System.out.println(fibH(43));
    }

    private static int fib(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        int result =  fib(n - 1) + fib(n - 2);
        return result % 1000000007;
    }

    // 动态规划思路解决斐波那契
    private static int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long first = 0;
        long sec = 1;
        for (int i = 2; i <= n; i++) {
            long naval = first + sec;
            first = sec;
            sec = naval % 1000000007;
        }
        return (int)sec;
    }

    private HashMap map = new HashMap<>();
    public int fibH(int n) {
        if(n == 0) {
            return 0;
        } else
        if(n == 1) {
            return 1;
        }
        if(map.get(n) == null) {
            map.put(n, fibH(n-1) + fibH(n-2));
        }
        return (int)map.get(n) % 1000000007;
    }

}
