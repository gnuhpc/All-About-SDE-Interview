package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.leetcode.utils.Utils;

import java.util.Arrays;

public class BuildTree106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) return null;
        TreeNode root = new TreeNode(postorder[postorder.length-1]);

        int inorderIdx = Utils.find(inorder,root.val);
        assert(inorderIdx!=-1);

        int[] inorderLeft = Arrays.copyOfRange(inorder,0,inorderIdx);
        int[] inorderRight= Arrays.copyOfRange(inorder, inorderIdx+1,inorder.length);

        int[] postorderLeft = Arrays.copyOfRange(postorder,0,inorderIdx);
        int[] postorderRight= Arrays.copyOfRange(postorder,inorderIdx,postorder.length-1);

        root.left = buildTree(inorderLeft,postorderLeft);
        root.right = buildTree(inorderRight,postorderRight);

        return root;

    }
}
