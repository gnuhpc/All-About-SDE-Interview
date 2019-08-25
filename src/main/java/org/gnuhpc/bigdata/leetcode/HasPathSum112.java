package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.Stack;

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

    public boolean hasPathSum3(TreeNode root, int sum) {
        // iteration method
        if (root == null) {return false;}
        Stack<TreeNode> path = new Stack<>();
        Stack<Integer> sub = new Stack<>();
        path.push(root);
        sub.push(root.val);
        while (!path.isEmpty()) {
            TreeNode temp = path.pop();
            int tempVal = sub.pop();
            if (temp.left == null && temp.right == null) {if (tempVal == sum) return true;}
            else {
                if (temp.left != null) {
                    path.push(temp.left);
                    sub.push(temp.left.val + tempVal);
                }
                if (temp.right != null) {
                    path.push(temp.right);
                    sub.push(temp.right.val + tempVal);
                }
            }
        }
        return false;
    }
}
