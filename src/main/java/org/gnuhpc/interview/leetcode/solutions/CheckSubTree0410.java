package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2020/10/12
 */
public class CheckSubTree0410 {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        else if (t1 == null || t2 == null) return false;
        else {
            return isSame(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
        }
    }

    //判断s和t两棵树是否相等
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
