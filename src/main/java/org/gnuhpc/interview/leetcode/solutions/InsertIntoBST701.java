package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 2020/1/16
 */
public class InsertIntoBST701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if (root == null) return new TreeNode(val);
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        return root;
    }

    /*
    Method 2: Non-recursive
     */

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode p = root;
        while (p != null) {
            if (val > p.val) {  //在右边
                if (p.right == null) {
                    p.right = new TreeNode(val);  //直接插入
                    return root;
                } else {
                    p = p.right;  //接着在右边查找
                }
            } else if (val < p.val) {
                if (p.left == null) {
                    p.left = new TreeNode(val);
                    return root;
                } else {
                    p = p.left;
                }
            }
        }
        return root;
    }

}
