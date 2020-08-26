package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2019/11/6
 */
public class SplitBST776 {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if (root == null)
            return new TreeNode[]{null, null};
        TreeNode[] ans = new TreeNode[2];
        if (root.val > V) {
            ans[1] = root;
            TreeNode[] left = splitBST(root.left, V);
            root.left = left[1];
            ans[0] = left[0];
        } else {
            ans[0] = root;
            TreeNode[] right = splitBST(root.right, V);
            root.right = right[0];
            ans[1] = right[1];
        }
        return ans;
    }
}
