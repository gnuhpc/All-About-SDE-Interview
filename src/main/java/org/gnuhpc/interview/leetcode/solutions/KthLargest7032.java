package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;

/**
 * Copyright gnuhpc 2021/1/23
 */
public class KthLargest7032 {
    private class BST {

        private class TreeNode {

            private final int val;
            // 结点的count包含自己，所以默认是1
            private int count = 1;
            private TreeNode left;
            private TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        private TreeNode root;

        public void add(int val) {
            root = add(root, val);
        }

        private TreeNode add(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }
            if (node.val > val) {
                node.left = add(node.left, val);
            } else if (node.val < val) {
                node.right = add(node.right, val);
            }
            // 元素重复 不添加进树但是count++
            node.count++;
            return node;
        }

        public TreeNode search(int k) {
            return search(root, k);
        }

        private TreeNode search(TreeNode node, int k) {
            if (node == null) {
                return node;
            }
            int leftNodeCount = node.left != null ? node.left.count : 0;
            int rightNodeCount = node.right != null ? node.right.count : 0;
            int currNodeCount = node.count - leftNodeCount - rightNodeCount;
            if (k > currNodeCount + rightNodeCount) {
                // k > 当前结点数加右子树的结点数，则搜索左子树
                return search(node.left, k - currNodeCount - rightNodeCount);
            } else if (k <= rightNodeCount) {
                // k <= 右子树的结点数，则搜索右子树
                return search(node.right, k);
            } else {
                // k == 当前结点数加右子树的结点数，则找到第k大的结点
                return node;
            }
        }
    }

    private final int k;
    private final BST bst = new BST();

    public KthLargest7032(int k, int[] nums) {
        this.k = k;
        for (int n : nums) {
            bst.add(n);
        }
    }

    public int add(int val) {
        bst.add(val);
        return bst.search(k).val;
    }

}
