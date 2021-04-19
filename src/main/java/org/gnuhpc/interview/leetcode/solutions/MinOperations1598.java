package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/3
 */
public class MinOperations1598 {
    public int minOperations(String[] logs) {
        int ans = 0;
        for (String log : logs) {
            if (log.equals("../")) {
                if (ans == 0) {
                    continue;
                } else {
                    ans--;
                }
            } else if (log.equals("./")) {
                continue;
            } else {
                ans++;
            }
        }
        return ans;
    }
}
