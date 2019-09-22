package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MinWinSubString76 {
    @Test
    public void test() {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s,t));
    }

    public String minWindow(String s, String t) {
        if (s.equals(t)) return s;
        int[] remains = new int[128]; // TODO remains是负数表示这个字母需要消除的为负值，即多出来的

        for (char c : t.toCharArray()) remains[c]++;

        int count = 0, begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;

        while (end < s.length()) {
            // 遇到需要消除的字母，包含总数+1
            if (remains[s.charAt(end)] > 0) {
                count++;
            }

            //每次经过一个字母都减1
            remains[s.charAt(end)]--;
            // end -->
            end++;

            //需要消除的总数为0的时候，尝试begin -->
            while (count == t.length()) {
                if (end - begin < d) {
                    d = end - begin;
                    head = begin;
                }

                if (remains[s.charAt(begin)] == 0)
                    count--;

                //首指针对应字符的技术还原
                remains[s.charAt(begin)]++;
                begin++;
            }
        }

        return d == Integer.MAX_VALUE ? "" : s.substring(head, head+d);
    }


}

