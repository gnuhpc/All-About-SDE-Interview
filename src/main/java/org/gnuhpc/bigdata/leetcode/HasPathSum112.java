package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class HasPathSum112 {
    /*
    Method : recursive
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null && sum - root.val == 0) {
            System.out.println(root.val);
            return true;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /*
    Method2 : preorder traversal
     */
    public boolean hasPathSum2(TreeNode root, int sum){
        if (root == null) return false;
        //前序遍历
        return helper(root, sum, 0);
    }

    private boolean helper(TreeNode root, int target, int curSum) {
        if (root == null) return false;
        curSum += root.val;
        if (root.left == null && root.right == null){
            return curSum == target;
        } else {
            return helper(root.left, target, curSum) || helper(root.right,target,curSum);
        }
    }
}
