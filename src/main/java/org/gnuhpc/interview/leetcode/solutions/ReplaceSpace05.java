package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/1
 */
public class ReplaceSpace05 {
    public String replaceSpace(String s) {
        if (s == null) {
            return s;
        }

        char[] array = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : array) {
            if (ch == ' ') {
                sb.append("%20");
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();

    }
}
