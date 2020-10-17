package com.zbl.leetcode;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Create in 2020/10/17
 * @Email: zbl5337@gmail.com
 * @Description: 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
 * <p>
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,5,1,3,4,7], n = 3
 * 输出：[2,3,5,4,1,7]
 * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4,4,3,2,1], n = 4
 * 输出：[1,4,2,3,3,2,4,1]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,2,2], n = 2
 * 输出：[1,2,1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 500
 * nums.length == 2n
 * 1 <= nums[i] <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-the-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test1470 {

    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 3, 4, 7};
        int n = 3;
        System.out.println(nums[0]);
        System.out.println(Arrays.toString(new Test1470().shuffle(nums, n)));
    }

    public int[] shuffle(int[] nums, int n) {
        int[] r = new int[nums.length];
        int x = 0;
        int y = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i < n) {
                if(x >= nums.length) {
                    continue;
                }
                r[x] = nums[i];
                x += 2;
            } else {
                if(y >= nums.length) {
                    continue;
                }
                r[y] = nums[i];
                y += 2;
            }
        }
        return r;
    }
}
