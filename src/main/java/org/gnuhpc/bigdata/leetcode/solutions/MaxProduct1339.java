package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 2020/2/29
 */
public class MaxProduct1339 {
    double ans = Double.MIN_VALUE;
    double allSum;
    double nodeSum;

    // If the a*b has to be maximum, then (allSum-b)*b should be maximum,
    // in which the allSum is fixed.
    public int maxProduct(TreeNode root) {
        allSum = sum(root);
        getNodeSum(root);
        return (int) (ans % (int) (1e9 + 7));
    }

    public double sum(TreeNode node) {
        if (node == null) return 0;
        return node.val + sum(node.left) + sum(node.right);
    }

    public double getNodeSum(TreeNode node) {
        if (node == null) return 0;
        nodeSum = node.val + getNodeSum(node.left) + getNodeSum(node.right);
        ans = Math.max(ans, (allSum - nodeSum) * nodeSum);
        return nodeSum;
    }
}
