package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/5/5
 */
public class DeleteAndEarn740 {
    // 记忆化数组
    private final int[] memo = new int[10001];

    public int deleteAndEarn(int[] nums) {
        int len = nums.length;

        int[] cnt = new int[10001];
        // 统计每个数字出现的次数
        for (int i = 0; i < len; i++) {
            ++cnt[nums[i]];
        }

        for (int i = 10000; i >= 1; i--) {
            if (cnt[i] != 0) return dfs(i, cnt);
        }
        return 0;
    }

    private int dfs(int num, int[] cnt) {
        if (num <= 0) return 0;
        if (cnt[num] == 0) return dfs(num - 1, cnt);
        if (memo[num] != 0) return memo[num];

        int res = cnt[num] * num;

        // 如果存在num-1，那么要对两种结果分别进行记忆化递归，然后取最大值结果
        if (cnt[num - 1] != 0) res = Math.max(dfs(num - 1, cnt), res + dfs(num - 2, cnt));
        else res += dfs(num - 2, cnt);

        memo[num] = res;

        return res;
    }
/*
作者：ggeorge500
链接：https://leetcode-cn.com/problems/delete-and-earn/solution/ji-yi-hua-shen-du-you-xian-sou-suo-by-gg-wjz1/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
