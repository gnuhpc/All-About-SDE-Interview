package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

public class BstToGst1038 {
    int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        if(root == null) return null;
        helper(root);

        return root;
    }


    private void helper(TreeNode root){
        if(root == null) return;
        helper(root.right);
        sum += root.val;
        root.val = sum;
        helper(root.left);
    }
}
