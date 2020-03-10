package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.List;

/**
 * Copyright gnuhpc 2019/12/14
 */
public class RangeSumBST938 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int rootVal = root.val;

        if (rootVal < L) return rangeSumBST(root.right, L, R);
        if (rootVal > R) return rangeSumBST(root.left, L, R);
        else return rootVal + rangeSumBST(root.left, L, rootVal) + rangeSumBST(root.right, rootVal, R);
    }
}
