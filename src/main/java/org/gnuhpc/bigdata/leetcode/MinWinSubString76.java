package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

//DONE
public class MinWinSubString76 {
    @Test
    public void test() {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s,t));
    }

    // https://www.youtube.com/watch?v=9qFR2WQGqkU
    public String minWindow(String s, String t) {
        if (s.equals(t)) return s;
        String res = "";
        int[] map = new int[128]; // map是负数表示这个字母需要消除的为负值，即超额多出来的

        for (char c : t.toCharArray()) map[c]++;

        int total = 0, left = 0, right = 0, minLength = Integer.MAX_VALUE, head = 0;

        while (right < s.length()) {
            // 遇到需要消除的字母，包含总数+1
            if (map[s.charAt(right)] > 0) {
                total++;
            }

            //每次经过一个字母都减1
            map[s.charAt(right)]--;
            // end -->
            right++;

            //需要消除的总数为0的时候，尝试begin -->
            while (total == t.length()) {
                if (right - left < minLength) { //更新最短长度
                    minLength = right - left;
                    head = left; //更新开始的index
                    res = s.substring(head, head+minLength);
                }

                if (map[s.charAt(left)] == 0)
                    total--;

                //首指针对应字符的技术还原
                map[s.charAt(left)]++;
                left++;
            }
        }

        return res;
    }

}

