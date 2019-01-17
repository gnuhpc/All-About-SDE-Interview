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

        System.out.println(lengthOfLongestSubstring2(str1));
        System.out.println(lengthOfLongestSubstring(str2));
        System.out.println(lengthOfLongestSubstring(str3));
        System.out.println(lengthOfLongestSubstring(str4));
        System.out.println(lengthOfLongestSubstring(str5));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        //char - position
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); /*start point can be recent dup's next char or last start*/
            start = Math.max(start, (map.containsKey(c)) ? map.get(c) + 1 : 0); /*if current str len is bigger then update*/
            max = Math.max(max, i - start + 1); /*add char to distanceMap with index*/
            map.put(c, i);
        }
        return max;
    }

    /*
    Method2 双指针
     */
    public static int lengthOfLongestSubstring2(String s) {
        // write your code here
        int[] cnt = new int[256];
        char[] sc = s.toCharArray();

        int ans = 0 , l = 0, r = 0;
        while (r<sc.length){
            char c = sc[r];
            cnt[c]++;
            while (cnt[c] > 1) {
                cnt[sc[l]]--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }
}
