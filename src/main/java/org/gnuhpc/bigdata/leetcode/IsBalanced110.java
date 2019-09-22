package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class IsBalanced110 {
    //O(nlogn) 最坏是O(n^2)
    /*
    Method1 : 递归分治 ，自顶而下
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int left = heightOfTree(root.left);
        int right = heightOfTree(root.right);

        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int heightOfTree(TreeNode root){
        if (root==null) return 0;

        int left = heightOfTree(root.left);
        int right = heightOfTree(root.right);

        return Math.max(left,right)+1;
    }


    /*
    Method2 : 递归，及时Return , 使用-1作为有问题的树，发现为-1则层层返回不再求具体高度
    O(n)，自底而上
     */
    public boolean isBalanced2(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null)
            return 0;

        int left = depth(root.left);
        if (left == -1)
            return -1;
        int right = depth(root.right);
        if (right == -1)
            return -1;

        if (Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }

}
