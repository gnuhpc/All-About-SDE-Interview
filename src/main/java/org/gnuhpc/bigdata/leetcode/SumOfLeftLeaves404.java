package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 19-7-10
 */
public class SumOfLeftLeaves404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;

        if (root.left!=null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        } else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }
}
