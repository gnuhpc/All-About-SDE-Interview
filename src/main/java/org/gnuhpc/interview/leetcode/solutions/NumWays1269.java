package org.gnuhpc.interview.leetcode.solutions;

public class NumWays1269 {
    private static final int MOD = 1000000007;

    private long[][] memo;

    private long backTrack(int from, int steps, int arrLen) {
        if (from == 0 && steps == 0) {
            return 1;
        }

        if (from > steps) {
            return 0;
        }

        if (memo[from][steps] != -1) {
            return memo[from][steps];
        }

        // 不动
        long nonMoveCount = backTrack(from, steps - 1,arrLen);

        // 向左
        long leftMoveCount = 0;
        if (from > 0) {
            leftMoveCount = backTrack(from - 1, steps - 1,arrLen);
        }

        // 向右
        long rightMoveCount = 0;
        if (from < arrLen - 1) {
            rightMoveCount = backTrack(from + 1, steps - 1,arrLen);
        }

        memo[from][steps] = (nonMoveCount + leftMoveCount + rightMoveCount) % MOD;

        return memo[from][steps];
    }

    public int numWays(int steps, int arrLen) {
        // 数组长度最多就是一半的步数+1, 否则走太远就回不来了。
        arrLen = Math.min(arrLen, steps / 2 + 1);

        memo = new long[arrLen][steps + 1];
        for (int i = 0; i < arrLen; i++) {
            for (int j = 0; j < steps + 1; j++) {
                memo[i][j] = -1;
            }
        }
        return (int) backTrack(0, steps, arrLen);
    }
}
