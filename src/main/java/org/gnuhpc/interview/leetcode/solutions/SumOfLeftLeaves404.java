package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 19-7-10
 */
public class SumOfLeftLeaves404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        } else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }
    /*
    Method2: PreOrder + pre pointer
     */

    int sum = 0;
    TreeNode pre = null;

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;

        helper2(root);

        return sum;
    }

    private void helper2(TreeNode root) {
        if (root == null) return;
        helper2(root.left);

        if (pre != null) {
            if (root.left == pre && pre.left == null && pre.right == null) sum += pre.val;
        }
        pre = root;

        helper2(root.right);
    }



    /*
    Method3: InOrder + pre pointer
     */

    public int sumOfLeftLeaves3(TreeNode root) {
        if (root == null) return 0;

        helper3(root);

        return sum;
    }

    private void helper3(TreeNode root) {
        if (root == null) return;

        if (pre != null) {
            if (pre.left == root && root.left == null && root.right == null) sum += root.val;
        }
        pre = root;
        helper3(root.left);
        helper3(root.right);
    }
}
