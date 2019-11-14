package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring3 {

    public static void main(String[] args) {
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
    public static int lengthOfLongestSubstring(String s) {
        // write your code here
        int[] map = new int[256];
        char[] sc = s.toCharArray();

        int ans = 0 , l = 0, r = 0;
        while (r<sc.length){
            char c = sc[r];
            map[c]++;//无论有没有重复，先加上，在while循环中处理
            while (map[c] > 1) { //直到这个重复的字符不再出现在新的字符串上
                map[sc[l]]--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }
}
