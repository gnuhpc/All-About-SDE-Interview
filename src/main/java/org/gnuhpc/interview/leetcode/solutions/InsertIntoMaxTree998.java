package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2020/10/12
 */
public class InsertIntoMaxTree998 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val) {
            TreeNode tmp = new TreeNode(val);
            tmp.left = root;
            return tmp;
        }
        TreeNode right = insertIntoMaxTree(root.right, val);
        root.right = right;
        return root;
    }
}
