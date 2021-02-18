package leetcode.editor.cn;//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 
//
// 示例 2： 
//
// 输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 
//
// 示例 3： 
//
// 输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 输入的数组长度范围为 [1, 10000] 
// 
//
// 
// Related Topics 堆 贪心算法 
// 👍 198 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution659 {
    public boolean isPossible(int[] nums) {
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
                return false;
            }

        }
        return false;

    }

}
//leetcode submit region end(Prohibit modification and deletion)
