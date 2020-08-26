package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/11/30
 */
public class BoundaryOfBinaryTree545 {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        if (root.left != null || root.right != null) res.add(root.val);
        left_bound(root.left, res);
        leaves(root, res);
        right_bound(root.right, res);

        return res;
    }

    private void left_bound(TreeNode root, List<Integer> res) {
        if (root == null || (root.left == null && root.right == null)) return;
        res.add(root.val);
        if (root.left == null)
            left_bound(root.right, res);
        else
            left_bound(root.left, res);
    }

    private void right_bound(TreeNode root, List<Integer> res) {
        if (root == null || (root.left == null && root.right == null)) return;
        if (root.right == null) right_bound(root.left, res);
        else right_bound(root.right, res);
        res.add(root.val);
    }

    private void leaves(TreeNode root, List<Integer> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) res.add(root.val);
        leaves(root.left, res);
        leaves(root.right, res);
    }

}
