package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/8/14
 */
public class GetLonelyNodes1469 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> getLonelyNodes(TreeNode root) {

        search(root);
        return res;
    }

    public void search(TreeNode root) {

        if (root == null) {
            return;
        }
        if (root.left != null && root.right == null) {
            res.add(root.left.val);

        }

        if (root.right != null && root.left == null) {
            res.add(root.right.val);

        }
        search(root.left);
        search(root.right);

    }
}
