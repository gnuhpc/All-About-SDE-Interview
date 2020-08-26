package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

public class ConvertBiNode1712 {
    TreeNode head=new TreeNode(0),pre=head;
    public TreeNode convertBiNode(TreeNode root) {
        if(root==null)
            return null;
        convertBiNode(root.left);
        pre.right=root;
        root.left=null;
        pre=root;
        convertBiNode(root.right);
        return head.right;
    }
}
