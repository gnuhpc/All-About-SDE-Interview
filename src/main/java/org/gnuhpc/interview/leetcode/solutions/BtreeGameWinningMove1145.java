package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2021/4/19
 */
public class BtreeGameWinningMove1145 {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode xRoot = findNode(root, x);
        int left = countNodes(xRoot.left);
        int right = countNodes(xRoot.right);
        int half = n / 2;
        return left > half || right > half || left + right < half;
    }

    private TreeNode findNode(TreeNode root, int x) {
        if (root == null) {
            return null;
        }
        if (root.val == x) {
            return root;
        }
        TreeNode left = findNode(root.left, x);
        TreeNode right = findNode(root.right, x);
        return left != null ? left : right;
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

}
