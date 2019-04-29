package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram242 {
    public static void main(String[] args) {
        String s = "anagrm", t = "nagaram";
        System.out.println(isAnagram(s,t));


    }

    public static boolean isAnagram(String s, String t) {
        Map<Character,Integer> charMap = new HashMap<>();

        for (char c : s.toCharArray()){
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
        }

        for (char c: t.toCharArray()){
            if (charMap.containsKey(c)){
                charMap.put(c,charMap.get(c)-1);
            } else {
                return false;
            }
        }

        return charMap.values().stream().filter(i->i!=0).count()==0;

    }

    /**
     * 用数组来统计字符的词频，作对比
     */
    public boolean isAnagram2(String s, String t) {
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
