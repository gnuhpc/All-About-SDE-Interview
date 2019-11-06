package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class DeleteNode450 {
    public TreeNode deleteNode(TreeNode root, int key) {

         /* Base Case: If the tree is empty */
        if (root == null)  return null;

        /* Otherwise, recur down the tree */
        if (key < root.val)
            root.left = deleteNode(root.left, key);
        else if (key > root.val)
            root.right = deleteNode(root.right, key);

            // if key is same as find's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            //这是重点
            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.val = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteNode(root.right, root.val);
        }

        return root;

    }

    private int minValue(TreeNode root) {
        int minv = root.val;
        while (root.left != null)
        {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }
}
