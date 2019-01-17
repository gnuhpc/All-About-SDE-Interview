package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.Stack;

public class InvertTree226 {
    public TreeNode invertTree(TreeNode root) {

        if (root==null || (root.left == null && root.right ==null)) return root;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;

    }

    public TreeNode invertTreeNonRecursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            TreeNode tmp = curNode.left;
            curNode.left = curNode.right;
            curNode.right = tmp;
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
        }
        return root;
    }
}
