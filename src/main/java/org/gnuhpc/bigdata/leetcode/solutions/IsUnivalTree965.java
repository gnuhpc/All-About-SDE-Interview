package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 2020/3/8
 */
public class IsUnivalTree965 {
    /*
    Method1: 记录全局变量法
     */
    int val = -1;

    public boolean isUnivalTree(TreeNode root) {
        if (root == null)
            return true;

        if (val == -1) val = root.val;
        else {
            if (val != root.val) return false;
        }

        return isUnivalTree(root.left) && isUnivalTree(root.right);

    }

    /*
    Method2: Preorder
     */

    public boolean isUnivalTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        return isUnivalTree2(root.left) && isUnivalTree2(root.right);
    }
}
