package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2019/11/30
 */
public class IsSubtree572 {
    //遍历s树，去比较s树的每一个节点作为根节点对应的子树是否和t相等
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        else if (s == null && t != null) return false;
        else if (s != null && t == null) return false;
        else {
            return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
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
