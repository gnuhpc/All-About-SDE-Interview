package org.gnuhpc.bigdata.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class MinWinSubString76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s,t));

    }

    public static String minWindow(String s, String t) {
        int[] remains = new int[128]; // remains是负数表示这个字母需要消除的为负值，即多出来的

        for (char c : t.toCharArray()) remains[c]++;

        int count = s.length(), begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;

        while (end < s.length()) {
            // 遇到需要消除的字母，需要消除的总数-1
            if (remains[s.charAt(end)] > 0) {
                count--;
            }

            //每次经过一个字母都减1
            remains[s.charAt(end)]--;
            // end -->
            end++;

            //需要消除的总数为0的时候，尝试begin -->
            while (count == 0) {
                if (end - begin < d) {
                    d = end - begin;
                    head = begin;
                }

                if (remains[s.charAt(begin)] == 0)
                    count++;

                //首指针对应字符的技术还原
                remains[s.charAt(begin)]++;
                begin++;
            }
        }

        return d == Integer.MAX_VALUE ? "" : s.substring(head, head+d);
    }
}

/*  双指针模板

        int findSubstring(string s){
            vector<int> distanceMap(128,0);
            int counter; // check whether the substring is valid
            int begin=0, end=0; //two pointers, one point to tail and one  head
            int d = s.length; //the length of substring

            for() { // initialize the hash distanceMap here  }

            while(end<s.size()){
                if(distanceMap[s[end++]]-- ?){  // modify counter here  }

                while(// counter condition ){

                    // update d here if finding minimum
                    //increase begin to make it invalid/valid again

                    if(distanceMap[s[begin++]]++ ?){ //modify counter here }
                }

            // update d here if finding maximum
            }
            return d;
        }
*/
