package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

//TODO 待整理
public class MinCut132 {
    //We cache each positions minimum depth from this position by min depth found- current depth(cut) .
    // If we hit a position again then we return the cut (current depth)+ minimum depth for this position.
    private int gen(String s, int pos, int cut, Map<Integer, Integer> cache) {
        if (pos >= s.length()) return cut - 1;
        int min = Integer.MAX_VALUE;
        if (cache.containsKey(pos)) {
            return cut + cache.get(pos);
        }
        for (int end = pos + 1; end <= s.length(); ++end) {
            String sub = s.substring(pos, end);
            if (isPalindrome(sub)) {
                min = Math.min(min, gen(s, end, cut + 1, cache));
            }
        }
        cache.put(pos, min - cut);
        return min;
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }

    public int minCut(String s) {
        return gen(s, 0, 0, new HashMap<>());
    }

    //DP
    int[] dp;

    public int minCut2(String s) {
        dp = new int[s.length() + 1];
        for (int i = 0; i < s.length() + 1; i++)
            dp[i] = i - 1;
        for (int i = 0; i < s.length(); i++) {
            update(s, i, i);
            update(s, i, i + 1);
        }
        return dp[s.length()];
    }

    private void update(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            dp[j + 1] = Math.min(dp[j + 1], dp[i] + 1);
            i--;
            j++;
        }
    }
}
