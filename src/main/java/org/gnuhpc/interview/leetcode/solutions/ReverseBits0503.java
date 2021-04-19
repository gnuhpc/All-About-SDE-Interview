package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/28
 */
public class ReverseBits0503 {
    public int reverseBits(int num) {
        if (num == -1) return 32;
        String s = Integer.toBinaryString(num);
        //System.out.println(s);
        String[] arr = s.split("0");
        if (arr.length < 1) {
            return arr.length + 1;
        }
        int max = arr[0].length();
        int res = max + 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].length() + arr[i].length() > max) {
                max = arr[i - 1].length() + arr[i].length();
                res = max + 1;
            }
        }
        return res;
    }
}
