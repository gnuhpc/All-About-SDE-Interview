package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 19-7-10
 */

public class PathSum437 {
    private int res = 0;

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return res;

        helper(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return res;
    }

    private void helper(TreeNode node, int sum) {
        if (node == null) return;

        if (sum == node.val)
            res++;

        helper(node.left, sum - node.val);
        helper(node.right, sum - node.val);
    }

}
