package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeTraversal;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
