package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

public class BstFromPreorder1008 {
    public TreeNode bstFromPreorder(int[] preorder) {
        return buildBST(preorder, 0, preorder.length);
    }


    private TreeNode buildBST(int[] preorder, int left, int right) {
        if (left == right)
            return null;
        if (right - left == 1) return new TreeNode(preorder[left]);

        TreeNode root = new TreeNode(preorder[left]);

        int i;

        for (i=left+1; i < right;i++){
            if(preorder[i]>preorder[left]) break;
        }

        root.left = buildBST(preorder, left + 1, i);
        root.right = buildBST(preorder, i, right);
        return root;
    }
}
