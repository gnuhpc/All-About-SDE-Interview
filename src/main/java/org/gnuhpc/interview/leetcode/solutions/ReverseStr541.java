package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/4
 */
public class ReverseStr541 {
    private char[] arr;

    public String reverseStr(String s, int k) {
        arr = s.toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; ) {
            int left = len - i;
            if (left < k) {
                reverse(i, len - 1);
                break;
            } else {
                reverse(i, i + k - 1);
                i += 2 * k;
            }
        }
        return new String(arr);
    }

    private void reverse(int i, int j) {
        char tmp = ' ';
        while (i < j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
