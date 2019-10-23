package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 2019/10/18
 */
public class CanPermutePalindrome266 {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128]; //ASCII码128个

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            map[arr[i] - '!']++; //为什么是感叹号? 因为是ASCII码的第一个非空格的可见字符.
        }

        int res = 0;

        for (int n : map) {
            if (n % 2 == 1) res++;
        }

        if (res <= 1) return true;
        else return false;
    }
}
