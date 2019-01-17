package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class SameTree100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return (p.val == q.val && isSameTree(p.right,q.right) && isSameTree(p.left,q.left));
    }
}
