package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/4/21
 */

public class NumberOfSubarrays1248 {

    /*
    (双指针) O(n)
    双指针扫描 r 在前，l 在后。
    如果当前位置是奇数，则更新计数器，如果当前 [l, r] 有了恰好 k 个奇数，则移动 l 直到不满足，期间统计出长度为 tot。
    让 ans 累加 tot。
    如果当前位置是偶数，则说明贡献的答案和上一次是奇数的时候一样，直接让 ans 累加上一次的 tot。

    时间复杂度
    每个位置最多遍历两次，故时间复杂度为 O(n)O(n)。

    空间复杂度
    仅需要常数的额外空间。
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;

        int cnt = 0, tot = 0, ans = 0;
        for (int r = 0, l = 0; r < n; r++) {
            if (isOdd(nums[r])) {
                cnt++;
                if (cnt == k) tot = 0;

                while (cnt == k) {
                    tot++;
                    if (isOdd(nums[l]))
                        cnt--;
                    l++;
                }
                ans += tot;
            } else {
                ans += tot;
            }
        }

        return ans;
    }

    private boolean isOdd(int n) {
        return n % 2 == 1;
    }


    @Test
    public void test() {
        System.out.println(numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
    }
}
