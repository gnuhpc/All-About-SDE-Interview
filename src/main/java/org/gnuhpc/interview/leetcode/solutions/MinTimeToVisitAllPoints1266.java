package org.gnuhpc.interview.leetcode.solutions;

public class MinTimeToVisitAllPoints1266 {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        int[] prev = points[0];
        int[] cur;
        for (int i = 1; i < points.length; ++i) {
            cur = points[i];
            ans += Math.max(
                    Math.abs(cur[0] - prev[0]),
                    Math.abs(cur[1] - prev[1])
            );
            prev = cur;
        }
        return ans;
    }
}

