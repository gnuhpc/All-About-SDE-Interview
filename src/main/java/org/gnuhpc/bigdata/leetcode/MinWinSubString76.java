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

    // https://www.youtube.com/watch?v=9qFR2WQGqkU
    public String minWindow(String s, String t) {
        if (s.equals(t)) return s;
        String res = "";
        int[] remains = new int[128]; // remains是负数表示这个字母需要消除的为负值，即超额多出来的

        for (char c : t.toCharArray()) remains[c]++;

        int total = 0, left = 0, right = 0, minLength = Integer.MAX_VALUE, head = 0;

        while (right < s.length()) {
            // 遇到需要消除的字母，包含总数+1
            if (remains[s.charAt(right)] > 0) {
                total++;
            }

            //每次经过一个字母都减1
            remains[s.charAt(right)]--;
            // end -->
            right++;

            //需要消除的总数为0的时候，尝试begin -->
            while (total == t.length()) {
                if (right - left < minLength) { //更新最短长度
                    minLength = right - left;
                    head = left; //更新开始的index
                    res = s.substring(head, head+minLength);
                }

                if (remains[s.charAt(left)] == 0)
                    total--;

                //首指针对应字符的技术还原
                remains[s.charAt(left)]++;
                left++;
            }
        }

        return res;
    }

    //https://www.itcodemonkey.com/article/15510.html
    public String minWindow2(String s, String t) {
        if(s==null||s.isEmpty()||t==null||t.isEmpty()) return "";
        int left=0, right=0;
        int[] needs = new int[256];
        int[] window = new int[256];
        for(int k=0; k< t.length(); k++){
            needs[t.charAt(k)]++;
        }
        int found=0;
        int length=Integer.MAX_VALUE;
        String res="";
        while(right<s.length()){
            if(found<t.length()){
                // 遇到需要消除的字母，包含总数+1
                if(needs[s.charAt(right)]>0){
                    window[s.charAt(right)]++;
                    if(window[s.charAt(right)]<=needs[s.charAt(right)]){
                        found++;
                    }
                }
                right++;
            }
            while(found==t.length()){
                if(right-left<length){
                    length=right-left;
                    res=s.substring(left,right);
                }
                if(needs[s.charAt(left)]>0){
                    window[s.charAt(left)]--;
                    if(window[s.charAt(left)]<needs[s.charAt(left)]){
                        found--;
                    }
                }
                left++;
            }
        }
        return res;
    }
}

