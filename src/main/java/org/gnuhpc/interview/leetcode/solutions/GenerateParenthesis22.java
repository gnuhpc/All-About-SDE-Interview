package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis22 {
    // 左括号、右括号一共得用几个
    private int len = 0;

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        len = 2 * n;

        dfs("", 0, 0, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        if (curStr.length() == len) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < len / 2) {
            dfs(curStr + "(", left + 1, right, res);
        }
        if (right < len / 2) {
            dfs(curStr + ")", left, right + 1, res);
        }
    }
}
