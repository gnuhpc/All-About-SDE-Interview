package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/25
 */
public class RepeatedStringMatch686 {
    public int repeatedStringMatch(String A, String B) {
        int count = 1;
        String temp = A;
        while (!temp.contains(B)) {
            if (temp.length() - B.length() >= A.length()) {//已经找不到B了
                return -1;
            }
            count++;
            temp += A;
        }
        return count;
    }
}
