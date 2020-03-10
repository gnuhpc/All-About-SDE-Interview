package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeTraversal;
import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.*;

public class PreorderTraversal144 {
    /*
    Method 1: recursive
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        return TreeTraversal.preorder(root);
    }

    /*
    Method 2: stack
     */
    public List<Integer> preorderTraversalNonRecursive(TreeNode root) {
        return TreeTraversal.preorderNonRecursive(root);
    }
}
