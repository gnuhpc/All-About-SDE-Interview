package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis22 {

    // 左括号、右括号各有几个
    int num;

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();//用于存放解空间
        this.num = n;
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
        if (left == num && right == num) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < num) {
            dfs(curStr + "(", left + 1, right, res);
        }
        if (right < num) {
            dfs(curStr + ")", left, right + 1, res);
        }
    }

}
