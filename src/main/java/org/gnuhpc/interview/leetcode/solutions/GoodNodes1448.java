package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2021/2/3
 */
public class GoodNodes1448 {
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = Integer.MIN_VALUE;

        int result = dfsNode(root, max);
        return result;
    }

    private int dfsNode(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        if (root.val >= max) {
            result += 1;
            max = root.val;
        }
        return result + dfsNode(root.left, max) + dfsNode(root.right, max);
    }
}
