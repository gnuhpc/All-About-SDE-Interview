package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 2020/2/29
 */
public class IncreasingBST897 {
    TreeNode prev  = null;
    TreeNode first = null;

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        return first;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        if (prev == null) {
            prev = root;
            first = root;
            prev.left = null;
        }
        else {
            prev.right = root;
            prev = root;
            prev.left = null;
        }
        inOrder(root.right);
    }
}
