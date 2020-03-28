package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

public class IsBalanced110 {
    //O(nlogn) 最坏是O(n^2)
    /*
        递归，及时Return , 使用-1作为有问题的树，发现为-1则层层返回不再求具体高度
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int left = heightOfTree(root.left);
        if (left == -1) return false;

        int right = heightOfTree(root.right);
        if (right == -1) return false;

        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int heightOfTree(TreeNode root) {
        if (root == null) return 0;

        int left = heightOfTree(root.left);
        int right = heightOfTree(root.right);

        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
