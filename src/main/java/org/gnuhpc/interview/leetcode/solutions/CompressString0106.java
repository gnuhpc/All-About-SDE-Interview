package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/6
 */
public class CompressString0106 {
    public String compressString(String S) {
        int N = S.length();
        int i = 0;
        char[] arr = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (i < N) {
            int j = i;
            while (j < N && arr[j] == arr[i]) {
                j++;
            }
            sb.append(arr[i]);
            sb.append(j - i);
            i = j;
        }

        if (sb.length() >= arr.length) return S;
        else return sb.toString();
    }
}
