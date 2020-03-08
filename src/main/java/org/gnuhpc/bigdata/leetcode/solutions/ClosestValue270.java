package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

/**
 * Copyright gnuhpc 2019/10/22
 */
public class ClosestValue270 {
    int res;

    public int closestValue(TreeNode root, double target) {
        res = root.val;
        helper(root, target);
        return res;
    }

    private void helper(TreeNode root, double target) {
        if (root == null) return;
        helper(root.left, target);
        if (Math.abs(root.val - target) < Math.abs(res - target)) {
            res = root.val;
        }

        helper(root.right, target);
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{4, 2, 5, 1, 3});
        System.out.println(closestValue(root, 3.714268));
    }
}
