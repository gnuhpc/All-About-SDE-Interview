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
        int[] map = new int[128]; // map是负数表示这个字母需要消除的为负值，即超额多出来，或者不该消除的

        for (char c : t.toCharArray()) map[c]++;

        int total = t.length(), left = 0, right = 0, minLength = Integer.MAX_VALUE, head = 0;

        char[] chS = s.toCharArray();

        while (right < s.length()) {
            // 遇到需要消除的字母，则消除且 end -->
            //如果这个if不成立，则不是要消除的字母，但是map也--了
            if (map[chS[right++]]-- > 0) {
                total--;//要匹配的总数减1
            }

            //需要消除的总数为0的时候，尝试begin -->
            while (total == 0) {
                if (right - left < minLength) { //更新最短长度
                    minLength = right - left;
                    head = left; //更新开始的index
                    res = s.substring(head, head + minLength);
                }

                //如果left对应的那个计数器是0，说明过去是命中的，则由于要向右移，需要total减小
                //首指针对应字符的技术还原
                if (map[chS[left++]]++ == 0)
                    total++;
            }
        }

        return res;
    }

}

