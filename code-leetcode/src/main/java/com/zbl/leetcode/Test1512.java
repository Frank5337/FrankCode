package com.zbl.leetcode;

/**
 * @Author: zbl
 * @Date: Created in 15:32 2020/8/25
 * @Description:
 * @Version: $
 * <p>
 * 1512. 好数对的数目
 * 给你一个整数数组 nums 。
 * <p>
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 * <p>
 * 返回好数对的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1,1,3]
 * 输出：4
 * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1,1]
 * 输出：6
 * 解释：数组中的每组数字都是好数对
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Test1512 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 1, 3};
        System.out.println(new Test1512().numIdenticalPairs(arr));
        System.out.println(new Test1512().numIdenticalPairs2(arr));
    }

    private int numIdenticalPairs(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    result++;
                }
            }
        }
        return result;
    }

    private int numIdenticalPairs2(int[] nums) {
        int ans = 0;
        //因为 1<= nums[i] <= 100  所以申请大小为100的数组
        //temp用来记录num的个数
        int[] temp = new int[100];
        /*
        从前面开始遍历nums
        假设nums = [1,1,1,1]
        第一遍
        temp是[0,0,0,0]
        ans+=0;
        temp[0]++;
        第二遍
        temp是[1,0,0,0]
        ans+=1;
        temp[0]++;
        第三遍
        temp=[2,0,0,0]
        ans+=2;
        temp[0]++;
        第四遍
        temp=[3,0,0,0]
        ans+=3;
        temp[0]++;
        */
        for (int num : nums) {
            /*
            这行代码可以写成
            ans+=temp[num - 1];
            temp[num - 1]++;
            */
            ans += temp[num - 1]++;
        }
        return ans;
    }

//    作者：f1oisVo7a9
//    链接：https://leetcode-cn.com/problems/number-of-good-pairs/solution/zhe-gu-ji-shi-wo-xie-zen-yao-duo-ti-yi-lai-zui-dua/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
