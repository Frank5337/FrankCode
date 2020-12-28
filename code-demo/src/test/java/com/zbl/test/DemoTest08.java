package com.zbl.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/3
 * @Description:
 * @Version: $
 */
public class DemoTest08 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,4,5};
        System.out.println(isPossible(arr));
    }

    public static boolean isPossible(int[] nums) {
        if (nums.length < 4) {
            return false;
        }
        int count = 0;
        List<Integer> list = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int sec = i + 1;
            int thr = i + 2;
            if (sec >= list.size() && thr >= list.size()) {
                break;
            }
            if (list.get(i) + 1 == list.get(i + 1) && list.get(i + 1) + 1 == list.get(i) + 2) {
                count++;
            }
            if (count == 3) {
                return true;
            }

        }
        return false;

    }
}
