package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class MaxDepth104 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
