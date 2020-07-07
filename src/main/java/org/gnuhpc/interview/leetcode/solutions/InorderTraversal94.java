package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeTraverse;
import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.*;

public class InorderTraversal94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        return TreeTraverse.inorder(root);
    }


    public List<Integer> inorderTraversalNonRecursive(TreeNode root) {
        return TreeTraverse.inorderNonRecursive(root);
    }
}
