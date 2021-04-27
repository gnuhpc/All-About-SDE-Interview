package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2021/4/25
 */
public class PurchasePlansLCP28 {
    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n && nums[i] < target; i++) {
            int low = i;
            int high = n - 1;
            while (high - low > 1) {
                int mid = (low + high) / 2;
                if (nums[mid] <= target - nums[i]) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
            if (low == n - 2 && nums[n - 1] <= target - nums[i]) low++;
            res = (res + low - i) % 1000000007;
        }
        return res;
    }
/*
作者：chizhou
链接：https://leetcode-cn.com/problems/4xy4Wx/solution/er-fen-by-chizhou-98th/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    /*
    Method2: two pointers
     */

    public int purchasePlans2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        Arrays.sort(nums);
        while (left != right) {
            if (nums[left] + nums[right] <= target) {
                ans += right - left;
                left++;
            } else {
                right--;
            }
            ans = ans % 1000000007;
        }
        return ans % 1000000007;
    }
}
