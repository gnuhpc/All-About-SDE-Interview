package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/*
 * 在300LIS问题基础上，加大了难度，
 * 我们考虑用2个数组分别记录以i结尾的结尾上升和结尾下降子序列
 * 用up[i]和down[i]分别记录到第i个元素为止以上升沿和下降沿结束的最长“摆动”
 * 序列长度，遍历数组，如果nums[i]>nums[i-1]，表明第i-1到第i个元素是上升的，
 * 因此up[i]只需在down[i-1]的基础上加1即可，
 * 如果nums[i]<nums[i-1]，表明第i-1到第i个元素是下降的，因此down[i]
 * 只需在up[i-1]的基础上加1即可，
 * 比较最终以上升沿和下降沿结束的
 * 最长“摆动”序列长度即可获取最终结果
 * */


public class WiggleMaxLength376 {
    // add by tina,method 1 DP
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        //一定要注意初始化为1
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) up[i] = Math.max(up[i], down[j] + 1);
                else if (nums[i] < nums[j]) down[i] = Math.max(down[i], up[j] + 1);
            }
        }
        //求2个序列中的最大值
        int mx = 0;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, up[i]);
            mx = Math.max(mx, down[i]);
        }
        return mx;
    }

    // add by tina,method 2 在上一方法的基础上做优化
    /*
     * 用up[i]和down[i]分别记录到第i个元素为止以上升沿和下降沿结束的最长“摆动”
     * 序列长度，遍历数组，如果nums[i]>nums[i-1]，表明第i-1到第i个元素是上升的，
     * 因此up[i]只需在down[i-1]的基础上加1即可，而down[i]保持down[i-1]不变；
     * 如果nums[i]<nums[i-1]，表明第i-1到第i个元素是下降的，因此down[i]
     * 只需在up[i-1]的基础上加1即可，而up[i]保持up[i-1]不变；如果nums[i]==nums[i-1]，
     * 则up[i]保持up[i-1]，down[i]保持down[i-1]。比较最终以上升沿和下降沿结束的
     * 最长“摆动”序列长度即可获取最终结果
     * */

    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length <= 0)
            return 0;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = up[i - 1] + 1;
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    /**
     * up表示到目前为止longest最后是上升的wiggle subsequence长度.
     * <p>
     * down表示到目前为止longest最后是下降的wiggle subsequence长度.
     * <p>
     * 递推时, 若num[i] > nums[i-1], 就用之前下降的最长wiggle subsequence长度+1.
     * <p>
     * 若nums[i] < nums[i-1], 就用之前上升的最长wiggle subsequence长度+1.
     * <p>
     * Time Complexity: O(nums.length).
     * <p>
     * Space: O(1).
     */
    //method 3
    public int wiggleMaxLength3(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

}
