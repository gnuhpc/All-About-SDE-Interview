package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

//From https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/jie-yu-shuihttps://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/jie-yu-shui
/*
对于位置 i，能够装的水为：
water[i] = min(
               # 左边最高的柱子
               max(height[0..i]),
               # 右边最高的柱子
               max(height[i..end])
            ) - height[i]
 */
public class Trap42 {
    //Method1 : 按列算
    //时间复杂度 O(N^2)，空间复杂度 O(1)。
    public int trap(int[] height) {
        int n = height.length;
        int ans = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < n - 1; i++) {
            int l_max = 0, r_max = 0;
            // 找右边最高的柱子 (包含自己)
            for (int j = i; j < n; j++)
                r_max = Math.max(r_max, height[j]);
            // 找左边最高的柱子 (包含自己)
            for (int j = i; j >= 0; j--)
                l_max = Math.max(l_max, height[j]);
            // 如果自己就是最高的话，
            // l_max == r_max == height[i]
            ans += Math.min(l_max, r_max) - height[i];
        }
        return ans;
    }

    /*
    Method2: 备忘录
    时间复杂度降低为 O(N)，已经是最优了，但是空间复杂度是 O(N)
     */
    public int trap2(int[] height) {
        if (height.length == 0) return 0;
        int n = height.length;
        int ans = 0;
        // 数组充当备忘录
        int[] l_max = new int[n], r_max = new int[n];
        // 初始化 base case
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        // 从左向右计算 l_max
        for (int i = 1; i < n; i++)
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        // 从右向左计算 r_max
        for (int i = n - 2; i >= 0; i--)
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        // 计算答案
        for (int i = 1; i < n - 1; i++)
            ans += Math.min(l_max[i], r_max[i]) - height[i];
        return ans;
    }

    //Method 3: 双指针
    public int trap3(int[] height) {
        if (height.length == 0) return 0;
        int n = height.length;
        int left = 0, right = n - 1;
        int ans = 0;

        int l_max = height[0];
        int r_max = height[n - 1];

        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // ans += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            }
            else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;

    }

    @Test
    public void test() {
        trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }
}
