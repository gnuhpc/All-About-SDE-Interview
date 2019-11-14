package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  Anagrams指几个string有相同的字符，但不同的字符顺序。
 */

public class FindAllAnagrams438 {
    @Test
    public void test(){
        String str1 = "cbaebabacd";
        String p1 = "abc";

        String str2 = "aaab";
        String p2 = "ba";

//        System.out.println(findAnagrams(str1,p1));
        System.out.println();
        System.out.println(findAnagrams2(str2,p2));
    }

    /**
     * 直观解法
     * O(m*n)
     * @param s
     * @param p
     * @return
     */

    public List<Integer> findAnagrams2(String s, String p) {
        if(s == null || s.length() == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int[] needs = new int[26]; //由于都是小写字母，因此直接用26个长度的数组代替原来的HashMap
        int[] window = new int[26];
        int left = 0, right = 0, total = p.length(); //用total检测窗口中是否已经涵盖了p中的字符
        for(char ch : p.toCharArray()){
            needs[ch - 'a'] ++;
        }
        while(right < s.length()){
            char chr = s.charAt(right);
            if(needs[chr - 'a'] > 0){
                window[chr - 'a']++;
                if(window[chr - 'a'] <= needs[chr - 'a']){
                    total--;
                }
            }
            while(total == 0){
                if(right-left+1 == p.length()){
                    res.add(left);
                }
                char chl = s.charAt(left);
                if(needs[chl - 'a'] > 0){
                    window[chl - 'a']--;
                    if(window[chl - 'a'] < needs[chl - 'a']){
                        total++;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }
}

