package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

public class FindTilt563 {
    /*
    Method1 : PostOrder
     */
    int result = 0;

    public int findTilt(TreeNode root) {
        postOrder(root);
        return result;
    }

    private int postOrder(TreeNode root) {
        if (root == null) return 0;

        int left = postOrder(root.left);
        int right = postOrder(root.right);

        result += Math.abs(left - right);

        return left + right + root.val;
    }

    /*
     Method2： Recursion
     */

    public int findTilt2(TreeNode root) {
        if (root == null) return 0;
        int tilt = Math.abs(findNodeWeight(root.left) - findNodeWeight(root.right));
        tilt += findTilt2(root.left);
        tilt += findTilt2(root.right);
        return tilt;
    }

    private int findNodeWeight(TreeNode root) {
        if (root == null) return 0;
        int weight = root.val;
        weight += findNodeWeight(root.left);
        weight += findNodeWeight(root.right);
        return weight;
    }
}
