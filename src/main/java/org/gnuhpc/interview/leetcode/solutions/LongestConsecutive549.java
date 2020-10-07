package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2020/10/5
 */
public class LongestConsecutive549 {
    public int longestConsecutive(TreeNode root) {
        return root == null ? 0 : Math.max(longestConsecutiveThrough(root),
                Math.max(longestConsecutive(root.left), longestConsecutive(root.right)));
    }

    private int longestConsecutiveThrough(TreeNode root) {
        return longestConsecutiveIncreasingFrom(root) + longestConsecutiveDecreasingFrom(root) - 1;
    }

    private int longestConsecutiveIncreasingFrom(TreeNode root) {
        int res = 0;
        if (root.left != null && root.left.val == root.val + 1)
            res = Math.max(res, longestConsecutiveIncreasingFrom(root.left));
        if (root.right != null && root.right.val == root.val + 1)
            res = Math.max(res, longestConsecutiveIncreasingFrom(root.right));
        return ++res;
    }

    private int longestConsecutiveDecreasingFrom(TreeNode root) {
        int res = 0;
        if (root.left != null && root.left.val == root.val - 1)
            res = Math.max(res, longestConsecutiveDecreasingFrom(root.left));
        if (root.right != null && root.right.val == root.val - 1)
            res = Math.max(res, longestConsecutiveDecreasingFrom(root.right));
        return ++res;
    }
}
