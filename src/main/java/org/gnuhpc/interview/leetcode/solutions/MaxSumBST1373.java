package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2021/1/23
 */
public class MaxSumBST1373 {
    int ans;
    final int ISBST = 0;
    final int SUM = 1;
    final int MIN = 2;
    final int MAX = 3;

    //多返回值
    int[] getNum(TreeNode root) {
        if (root == null) return new int[]{1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] l = getNum(root.left);
        int[] r = getNum(root.right);
        if (l[ISBST] != 1 || r[ISBST] != 1 || root.val <= l[MAX] || root.val >= r[MIN]) return new int[]{0, 0, 0, 0};
        int s = l[SUM] + r[SUM] + root.val;
        ans = Math.max(ans, s);
        return new int[]{1, s, Math.min(l[MIN], root.val), Math.max(r[MAX], root.val)};

    }

    public int maxSumBST(TreeNode root) {
        if (root == null) return 0;
        getNum(root);
        return ans;
    }

}
