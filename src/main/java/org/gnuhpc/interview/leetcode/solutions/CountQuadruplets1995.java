package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/12/29
 */
public class CountQuadruplets1995 {
    int count;
    int len;

    public int countQuadruplets(int[] nums) {
        len = nums.length;
        count = 0;
        if (len < 4) return 0;
        dfs(nums, 0, 0, 0);
        return count;
    }

    public void dfs(int[] nums, int depth, int sum, int begin) {
        if (depth == 3) {
            for (int i = begin; i < len; i++) {
                if (sum == nums[i]) {
                    count++;
                }
            }
            return;
        }
        for (int i = begin; i < len - 1; i++) {
            sum += nums[i];
            dfs(nums, depth + 1, sum, i + 1);
            sum -= nums[i];
        }
    }
/*
作者：sl2607-i
链接：https://leetcode-cn.com/problems/count-special-quadruplets/solution/java-hui-su-by-sl2607-i-oeix/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    public int countQuadruplets2(int[] nums) {
        int[] cnt = new int[301];//nums[i]<100，因此三个相加最大不超过300
        int res = 0;
        int n = nums.length;
        for (int c = n - 2; c >= 2; c--) {
            cnt[nums[c + 1]]++;
            for (int a = 0; a < c; a++) {
                for (int b = a + 1; b < c; b++) {
                    res += cnt[nums[a] + nums[b] + nums[c]];
                }
            }
        }
        return res;
    }
/*
作者：hui-fei-de-qi-e-m
链接：https://leetcode-cn.com/problems/count-special-quadruplets/solution/shu-zu-dai-ti-ha-xi-by-hui-fei-de-qi-e-m-fz0e/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
}
