package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/6
 */
//https://leetcode.jp/leetcode-1335-minimum-difficulty-of-a-job-schedule-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
public class MinDifficulty1335 {
    int[][] memo; // 记忆数组

    public int minDifficulty(int[] jobDifficulty, int d) {
        // 如果d大于数组元素个数，无法分组，返回-1
        if (d > jobDifficulty.length) return -1;
        // 初始化记忆数组
        memo = new int[d + 1][jobDifficulty.length];
        // 递归求解
        return help(jobDifficulty, d, 0);
    }

    // jobDifficulty：原始数组
// d：剩余尚未分组个数
// job：当前开始job下标
// 返回值：将下标job开始到数组结尾区间分成d组，得到的最小难度值
    int help(int[] jobDifficulty, int d, int job) {
        // 如果记忆数组中存在该值，直接返回
        if (memo[d][job] > 0) return memo[d][job];
        // 当前区间内最大值
        int maxDifficult = 0;
        // 返回结果
        int res = Integer.MAX_VALUE;
        // 当前区间最大结束坐标
        int end = jobDifficulty.length - d;
        // 循环每一个结束坐标
        for (int i = job; i <= end; i++) {
            // 更新当前区间内最大值
            maxDifficult = Math.max(maxDifficult, jobDifficulty[i]);
            // 如果所剩分组个数大于1，继续递归分组
            if (d > 1) {
                // 当前区间最大值加上子问题的结果，为当前解，
                // 利用当前解更新最优解
                res = Math.min(res, maxDifficult + help(jobDifficulty, d - 1, i + 1));
            }
        }
        // 如果尚未分组个数为1，返回当前区间内最大值
        if (d == 1) res = maxDifficult;
        // 将当前最优解保存至记忆数组
        memo[d][job] = res;
        return res;
    }
}
