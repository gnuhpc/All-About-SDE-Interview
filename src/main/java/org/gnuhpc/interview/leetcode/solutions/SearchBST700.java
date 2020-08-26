package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2019/10/22
 */
public class SearchBST700 {
    TreeNode res = null;

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) res = null;
        else if (root.val == val) res = root;
        else if (root.val > val) searchBST(root.left, val);
        else if (root.val < val) searchBST(root.right, val);

        return res;
    }
}
