package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram242 {
    @Test
    public void test() {
        String s = "anagrm", t = "nagaram";
        System.out.println(isAnagram(s,t));
    }

    /**
     * Method: 用数组来统计字符的词频，作对比
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] freq = new int[26];
        for(int i = 0; i< 26;i++){
            freq[i] = 0;
        }

        char c = 0;
        for(int i = 0;i<s.length();i++){
            c = s.charAt(i);
            freq[c-'a'] ++;
        }

        for(int i = 0;i<t.length();i++){
            c = t.charAt(i);
            freq[c -'a'] --;
        }

        for(int i = 0;i<26;i++){
            if(freq[i] != 0) return false;
        }

        return true;
    }
}
