package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/18
 */
public class ToLowerCase709 {
    public String toLowerCase(String str) {
        if (str == null || str.length() == 0) return str;
        char[] arr = str.toCharArray();
        char[] res = new char[arr.length];
        for (int i = 0; i < res.length; i++) {
            char c = arr[i];
            if (c >= 'A' && c <= 'Z') {
                c += 32;
            }
            res[i] = c;
        }

        return new String(res);
    }
}
