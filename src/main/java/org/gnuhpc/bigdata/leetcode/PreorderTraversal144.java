package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeTraversal;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

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

    /**
     * nonrecursive
     */
    public List<Integer> preorderTraversalNonRecur(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> preorder = new ArrayList<Integer>();

        if (root == null) {
            return preorder;
        }

        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return preorder;
    }


}
