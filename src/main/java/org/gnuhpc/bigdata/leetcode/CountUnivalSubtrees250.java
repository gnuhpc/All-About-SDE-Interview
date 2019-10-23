package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 2019/10/19
 */
public class CountUnivalSubtrees250 {
    public int countUnivalSubtrees(TreeNode root) {
        int[] arr = new int[1];
        postOrder(arr, root);
        return arr[0];
    }

    public boolean postOrder(int[] arr, TreeNode node) {
        if (node == null) return true;
        boolean left = postOrder(arr, node.left);
        boolean right = postOrder(arr, node.right);
        if (left && right) {
            if (node.left != null && node.left.val != node.val) return false;
            if (node.right != null && node.right.val != node.val) return false;
            arr[0]++;
            return true;
        }
        return false;
    }
}
