package com.zbl.leetcode.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zbl
 * @Date: Created in 2021/7/14 9:48
 * @Description:
 * @Version: $
 */
public class Test53 {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int res = Test53.maxSubArray(nums);
        System.out.println(res);
    }

    public static int maxSubArray(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int res = nums[0];
        int n = 0;
        for(int i = 0; i < nums.length; i++) {
            n = Math.max(n + nums[i], nums[i]);
            res = Math.max(res, n);
        }
        return res;
    }

    @Test
    public void test01() throws Exception {
//        Map map = new HashMap<>();
//        map.put("aaa","aaa");
//        System.out.println(null == map.get(null));
        int[] digits = {8,9,9,9};
        List<Integer> result = new ArrayList();
        int incr = 1;
        for(int i = digits.length - 1; i >= 0; i--) {
            if((digits[i] + incr) > 8 + incr) {
                result.add(0);
                incr = 1;
                if (i == 0) {
                    result.add(incr);
                }
            } else {
                result.add(digits[i] + incr);
                incr = 0;
            }
        }
        int[] res = new int[result.size()];
        for (int i = result.size() - 1, j = 0; i >=0 ; i--, j++) {
            res[j] = result.get(i);
        }
        System.out.println(Arrays.toString(res));

    }

    @Test
    public void test02() throws Exception {
        Stack stack = new Stack();

    }
}
