package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/24
 */
public class Maximum69Number1323 {
    public int maximum69Number(int num) {
        String str = String.valueOf(num);

        char[] arr = str.toCharArray();
        int i = str.indexOf('6');
        if (i == -1) return num;
        else arr[i] = '9';
        return Integer.valueOf(new String(arr));
    }
}
