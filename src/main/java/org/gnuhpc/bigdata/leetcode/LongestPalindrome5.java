package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class LongestPalindrome5 {
    @Test
    public void test(){
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }


    /**
     * O(n^2) Time, O(1) Space
     * Expand from center character and center of two chars
     * Update result according to the returned length
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        String longest = s.substring(0, 1);
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            //求出奇数点为中心的情况
            String s1 = expandAroundCenter(s, i, i);
            //求出偶数点为中心的情况
            String s2 = expandAroundCenter(s, i, i + 1);
            if (s1.length() > longest.length()) longest = s1;
            if (s2.length() > longest.length()) longest = s2;
        }
        return longest;
    }

    /**
     * Search for range in both direction
     */

    private String expandAroundCenter(String s, int i, int j) {
        int l = i;
        int r = j;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r); // note the range is from l + 1 to r - 1  都是闭区间
    }
}
