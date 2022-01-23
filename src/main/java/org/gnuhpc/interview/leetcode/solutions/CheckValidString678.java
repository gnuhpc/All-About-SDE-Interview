package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class CheckValidString678 {
    /*
    Method1: DFS LTE
     */
    private String source;

    public boolean checkValidString(String s) {
        source = s;
        return dfs(0, 0);
    }

    private boolean dfs(int start, int lCount) {
        if (lCount < 0) return false;
        if (start == source.length()) {
            return lCount == 0;
        }
        if (source.charAt(start) == '(') {
            return dfs(start + 1, lCount + 1);
        } else if (source.charAt(start) == ')') {
            return dfs(start + 1, lCount - 1);
        } else {
            //Three situations: ( , ) , blank
            return dfs(start + 1, lCount + 1) || dfs(start + 1, lCount - 1) || dfs(start + 1, lCount);
        }
    }

    /*
    Method2: Two scans from left and right
     * 正反两遍
     * 正时 将*当'('
     * 反时 将*当')'
     */
    public boolean checkValidString2(String s) {
        int left = 0, right = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') ++left;
            else --left;
            if (left < 0) return false;
        }
        if (left == 0) return true;
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == ')' || s.charAt(i) == '*') ++right;
            else --right;
            if (right < 0) return false;
        }
        return true;
    }


    /* Method3: https://leetcode.com/articles/valid-parenthesis-string/
     */
    public boolean checkValidString3(String s) {
        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            lo += c == '(' ? 1 : -1;
            hi += c != ')' ? 1 : -1;
            if (hi < 0) break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }

    @Test
    public void test() {
        System.out.println(checkValidString("(*)"));
    }
}
