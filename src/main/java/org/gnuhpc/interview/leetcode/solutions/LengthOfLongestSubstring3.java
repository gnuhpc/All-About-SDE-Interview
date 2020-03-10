package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring3 {

    @Test
    public void test() {
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";
        String str4 = "c";
        String str5 = "aab";

        System.out.println(lengthOfLongestSubstring(str1));
        System.out.println(lengthOfLongestSubstring(str2));
        System.out.println(lengthOfLongestSubstring(str3));
        System.out.println(lengthOfLongestSubstring(str4));
        System.out.println(lengthOfLongestSubstring(str5));
    }

    /*
    Method 双指针
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        int[] map = new int[256];
        char[] chS = s.toCharArray();

        int ans = 0, l = 0, r = 0;
        while (r < chS.length) {
            char c = chS[r++];
            map[c]++;//无论有没有重复，先加上，在while循环中处理
            while (map[c] > 1) { //直到这个重复的字符不再出现在新的字符串上
                map[chS[l++]]--;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
