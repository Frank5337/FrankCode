package com.zbl.wwj.concurrent.step2.p72;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 10:10
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.subsetsWithDup(new int[]{1, 2, 2});
    }


    Set<List<Integer>> ans = new HashSet<>();//结果集

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans.add(new ArrayList<Integer>());
        Arrays.sort(nums);//递归求解之前别忘对数组进行排序
        getSets(new ArrayList<List<Integer>>(), 0, nums);//help集合用来存放从当前ans复制过来的内容
        return new ArrayList<>(ans);

    }

    private void getSets(List<List<Integer>> help, int i, int[] nums) {//递归求幂集
        if (i == nums.length) return;//递归出口
        for (List<Integer> e : ans) help.add(e);
        for (List<Integer> E : help) {
            List<Integer> t = new ArrayList<Integer>(E);
            t.add(nums[i]);
            ans.add(t);
        }
        help.clear();//清空help集合
        System.out.println(Arrays.asList(ans));
        getSets(help, i + 1, nums);
    }


}
