package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.trie.Trie;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LongestCommonPrefix14 {
    /*
    排序法
     */
    //O(nlogn)
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        Arrays.sort(strs);

        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();

        int i;
        for (i = 0; i < Math.min(first.length, last.length) && first[i] == last[i]; i++) ;

        return strs[0].substring(0, i);
    }

    /*
    Method2: Trie Prefix
     */
    public String longestCommonPrefix2(String[] strs) {
        Trie trie = new Trie();

        for (String s : strs) trie.insert(s);

        return trie.longestCommonPrefix();
    }


    /*
    Metho3: 二分 二分直接猜答案, 适用于答案在一个区间的题目
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1) return strs[0];
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 0;
        int high = minLen;
        while (low + 1 < high) {
            int middle = low + (high - low) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle;
            else
                high = middle;
        }


        if (isCommonPrefix(strs, high)) {
            System.out.println("high:" + high);
            return strs[0].substring(0, high);
        } else {
            System.out.println("low:" + low);
            return strs[0].substring(0, low);
        }
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }


    @Test
    public void test() {
        String[] strs = new String[]{"al", "all", "alp", "alde"};
        assertEquals("al", longestCommonPrefix(strs));
        assertEquals("al", longestCommonPrefix2(strs));
        assertEquals("al", longestCommonPrefix3(strs));
    }

    // add by tina,1ms
    // 暴力解 o(strs.length*strs[i].length())
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        /* 可以先找到最短的那个,放在最前边
        int minIdx = 0;
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength){
                minIdx = i;
                minLength = strs[i].length();
            }
        }

        String temp = strs[0];
        strs[0] = strs[minIdx];
        strs[minIdx] = temp;
         */
        StringBuilder sb = new StringBuilder("");
        int flen = strs[0].length();
        for (int j = 0; j < flen; j++) {
            char c = strs[0].charAt(j);
            for (int i = 1; i < strs.length; i++) {
                if (j >= strs[i].length()
                        || strs[i].charAt(j) != c) return sb.toString();
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
