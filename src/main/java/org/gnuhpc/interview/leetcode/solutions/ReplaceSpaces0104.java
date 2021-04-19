package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/19
 */
public class ReplaceSpaces0104 {
    public String replaceSpaces(String S, int length) {
        char[] chs = new char[length * 3];
        int j = 0;
        for (int i = 0; i < length; i++) {
            char ch = S.charAt(i);
            if (ch == ' ') {
                chs[j++] = '%';
                chs[j++] = '2';
                chs[j++] = '0';
            } else {
                chs[j++] = ch;
            }
        }
        return new String(chs, 0, j);

    }
}
