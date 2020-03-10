package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeTraversal;
import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.*;

public class InorderTraversal94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        return TreeTraversal.inorder(root);
    }


    public List<Integer> inorderTraversalNonRecursive(TreeNode root) {
        return TreeTraversal.inorderNonRecursive(root);
    }
}
