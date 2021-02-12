package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/5
 */
public class EqualSubstring1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0, right = 0;
        int len = s.length();

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        int[] costArr = new int[len];
        for (int i = 0; i < len; i++) {
            costArr[i] = Math.abs(sArr[i] - tArr[i]);
        }

        int res = Integer.MIN_VALUE;
        int cost = 0;

        while (right < len) {
            cost += costArr[right];
            right++;

            while (left < len && cost > maxCost) {
                cost -= costArr[left];
                left++;
            }

            res = Math.max(right - left, res);

        }

        return res;
    }
}
