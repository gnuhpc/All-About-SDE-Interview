package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.Arrays;

//注意，必须有一个是中序遍历才能完成唯一树的构造
public class BuildTree105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || inorder.length == 0 || preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1) return root;
        int breakindex = findElem(inorder,root.val);
        int[] subleftpre = Arrays.copyOfRange(preorder, 1, breakindex + 1);
        int[] subrightpre = Arrays.copyOfRange(preorder, breakindex + 1, preorder.length);
        int[] subleftin = Arrays.copyOfRange(inorder, 0, breakindex);
        int[] subrightin = Arrays.copyOfRange(inorder, breakindex + 1, inorder.length);
        root.left = buildTree(subleftpre, subleftin);
        root.right = buildTree(subrightpre, subrightin);
        return root;
    }

    private int findElem(int arr[], int value) {
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                idx = i;
                break;
            }
        }

        return idx;
    }
}
