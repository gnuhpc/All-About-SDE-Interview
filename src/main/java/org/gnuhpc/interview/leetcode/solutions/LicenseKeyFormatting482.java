package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/12
 */
public class LicenseKeyFormatting482 {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        char[] arr = S.toUpperCase().toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != '-') {
                sb.append(arr[i]);
                count++;
                if (count % K == 0) {
                    sb.append('-');
                }
            }
        }

        int size = sb.length();
        if (size > 0 && sb.charAt(size - 1) == '-') {
            sb.delete(size - 1, size);
        }

        return sb.reverse().toString();
    }
}
