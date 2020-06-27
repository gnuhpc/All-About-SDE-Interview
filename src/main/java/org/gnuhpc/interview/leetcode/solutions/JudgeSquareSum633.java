package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/6/15
 */
public class JudgeSquareSum633 {
    public boolean judgeSquareSum(int target) {
        if (target < 0) return false;
        int i = 0, j = (int) Math.sqrt(target);
        while (i <= j) {
            int powSum = i * i + j * j;
            if (powSum == target) {
                return true;
            } else if (powSum > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
