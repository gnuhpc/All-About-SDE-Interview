package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/30
 */
public class MinCost1578 {
    public int minCost(String s, int[] cost) {
        // j永远比i大
        int i = 0, j = 1;
        int sum = 0;
        while (j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                // 前后相等
                if (cost[i] < cost[j]) {
                    // cost[i]值小，记录，i后移到j的位置，j后移1位
                    sum += cost[i];
                    // i，j同时向后移1位
                    i = j++;
                } else {
                    // cost[j]值小，记录，i不动，j后移1位
                    sum += cost[j++];
                }
            } else {
                // 前后不等，i，j同时向后移1位
                i = j++;
            }
        }
        return sum;
    }
}
