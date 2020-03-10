package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2019/11/6
 */
public class FindTarget653 {

    TreeNode root;

    public boolean findTarget(TreeNode root, int k) {
        this.root = root;
        return find(root, k);
    }

    private boolean find(TreeNode n, int k) {
        if (n == null)
            return false;

        if (2 * n.val != k && exist(k - n.val))
            return true;

        return find(n.left, k) || find(n.right, k);
    }

    private boolean exist(int v) {
        TreeNode n = root;
        for (; n != null; ) {
            if (v < n.val)
                n = n.left;
            else if (v > n.val)
                n = n.right;
            else {
                return true;

            }

        }
        return false;
    }

    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> hashset = new HashSet<>();
        return preOrder(root, hashset, k);
    }

    public boolean preOrder(TreeNode root, Set<Integer> hashset, int k) {
        if (root == null)
            return false;
        if (hashset.contains(k - root.val)) {
            return true;
        }
        hashset.add(root.val);
        return preOrder(root.left, hashset, k) || preOrder(root.right, hashset, k);
    }
}
