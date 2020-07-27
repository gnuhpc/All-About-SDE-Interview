package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

public class IsBalanced110 {
    //O(nlogn) 最坏是O(n^2)
    /*
        递归，及时Return , 使用-1作为有问题的树，发现为-1则层层返回不再求具体高度
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int depth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

}
