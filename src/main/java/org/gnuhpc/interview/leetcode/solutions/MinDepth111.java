package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

public class MinDepth111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        else if (root.left != null && root.right != null)
            return Math.min(minDepth(root.right), minDepth(root.left)) + 1;
        else if (root.left == null) return minDepth(root.right) + 1;
        else return minDepth(root.left) + 1;
    }
}
