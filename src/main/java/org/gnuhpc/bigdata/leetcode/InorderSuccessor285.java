package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 2019/10/20
 */
public class InorderSuccessor285 {
    /*
    Method1: 递归
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }

    //Follow up
    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val >= p.val) {
            return predecessor(root.left, p);
        }
        else {
            TreeNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }

    /*
    Method2 : 非递归
    The inorder traversal of a BST is the nodes in ascending order.
    To find a successor, you just need to find the  smallest one that is larger than the given value since
    there are no duplicate values in a BST.
    It just like the binary search in a sorted list.
    The time complexity should be `O(h)` where h is the depth of the result node.
    `succ` is a pointer that keeps the possible successor.
    Whenever you go left the current root is the new possible successor,
    otherwise the it remains the same.
    Only in a balanced BST `O(h) = O(log n)`. In the worst case `h` can be as large as `n`.

     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return succ;
    }


}
