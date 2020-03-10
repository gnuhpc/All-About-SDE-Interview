package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram242 {
    @Test
    public void test() {
        String s = "anagrm", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    /**
     * Method: 用数组来统计字符的词频，作对比
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];
        for (int i = 0; i < 26; i++) {
            freq[i] = 0;
        }

        char c = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            freq[c - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            c = t.charAt(i);
            freq[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) return false;
        }

        return true;
    }

    // add by tina,不如上面的写法，空间复杂度比较高
    public boolean isAnagram2(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int n = s.length();
        int[] ss = new int[26];
        int[] tt = new int[26];

        for (int i = 0; i < n; i++) {
            ss[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            tt[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (ss[i] != tt[i]) return false;
        }
        return true;
    }
}
