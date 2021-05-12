package org.gnuhpc.interview.leetcode.solutions;

public class CountGoodRectangles1725 {
    public int countGoodRectangles(int[][] rectangles) {
        int maxLen = 0, ans = 0;
        for (int[] rect : rectangles) {
            int len = Math.min(rect[0], rect[1]);
            if (len > maxLen) {
                maxLen = len;
                ans = 1;
            } else if (len == maxLen) {
                ans++;
            }
        }
        return ans;
    }
}
