package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/8
 */
public class StreamRank1010 {
    private TreeNode BST = null;

    public StreamRank1010() {
    }

    public void track(int x) {
        BST = insert(BST, x);
    }

    private TreeNode insert(TreeNode root, int x) {
        if (root == null) {
            return new TreeNode(x);
        }
        if (root.value == x) {
            root.count++;
        } else if (root.value > x) {
            root.count++;
            root.left = insert(root.left, x);
        } else {
            root.right = insert(root.right, x);
        }
        return root;
    }

    public int getRankOfNumber(int x) {
        return getNodeRank(BST, x, 0);
    }

    private int getNodeRank(TreeNode root, int x, int baseCount) {
        if (root == null) {
            return baseCount;
        }
        if (root.value == x) {
            return baseCount + root.count;
        } else if (root.value > x) {
            return getNodeRank(root.left, x, baseCount);
        } else {
            return getNodeRank(root.right, x, baseCount + root.count);
        }
    }

    private class TreeNode {
        private final int value;
        private int count;
        private TreeNode left;

        private TreeNode right;

        TreeNode(int v) {
            value = v;
            count = 1;
            left = null;
            right = null;
        }
    }
}
