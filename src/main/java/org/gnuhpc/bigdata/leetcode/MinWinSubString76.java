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

    // https://www.youtube.com/watch?v=9qFR2WQGqkU
    public String minWindow2(String s, String t) {
        if (s == null || t == null || s.length() < t.length())
            return "";
        // HashMap的key为t中各个字符，value为对应字符个数
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c))
                map.put(c, 0);
            map.put(c, map.get(c) + 1);
        }
        // minLeft为最小窗口左下标，minLen为最小长度，count用来计数
        int minLeft = 0, minLen = s.length() + 1, count = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 如果map.get(c)说明t中还有字符没有包含，计数器+1
                if (map.get(c) > 0){
                    count++;
                }
                map.put(c, map.get(c) - 1);
            }
            // 如果left到i中包含t中所有字符
            while (count == t.length()) {
                if (i - left + 1 < minLen) {
                    minLeft = left;
                    minLen = i - left + 1;
                }
                c = s.charAt(left);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                    if (map.get(c) > 0)
                        count--;
                }
                left++;
            }
        }
        if (minLen > s.length())
            return "";

        return s.substring(minLeft, minLeft + minLen);
    }

    public String minWindow3(String s, String t) {
        int[] tFreq = new int[256];
        for(int i = 0 ; i < t.length() ; i ++)
            tFreq[t.charAt(i)] ++;

        int[] sFreq = new int[256];;
        int sCnt = 0;

        int minLength = s.length() + 1;
        int startIndex = -1;

        int l = 0, r = -1;  //考察[l,r+1]窗口
        while(l < s.length()){//注意条件
            if(r + 1 < s.length() && sCnt < t.length()){

                sFreq[s.charAt(r+1)] ++;
                if(sFreq[s.charAt(r+1)] <= tFreq[s.charAt(r+1)])
                    sCnt ++;
                r ++;
            }
            else{
                //assert(sCnt <= t.size());
                if(sCnt == t.length() && r - l + 1 < minLength){
                    minLength = r - l + 1;
                    startIndex = l;
                }

                sFreq[s.charAt(l)] --;
                if(sFreq[s.charAt(l)] < tFreq[s.charAt(l)])
                    sCnt --;
                l ++;
            }
        }

        if( startIndex != -1 )
            return s.substring(startIndex, startIndex+minLength);

        return "";
    }


}

