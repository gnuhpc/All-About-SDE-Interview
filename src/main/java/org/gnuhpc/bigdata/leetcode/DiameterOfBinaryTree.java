package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class DiameterOfBinaryTree {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }


    //通过求最大深度的过程中，积累max
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }
}
