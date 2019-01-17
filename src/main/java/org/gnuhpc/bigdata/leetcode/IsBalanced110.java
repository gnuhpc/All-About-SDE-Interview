package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class IsBalanced110 {
    //O(nlogn)
    /*
    Method1 : 递归分治
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

    /*
    Method3 : ResultType 方法
     */

    class ResultType {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }

    public boolean isBalanced3(TreeNode root) {
        return helper(root).isBalanced;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // subtree not balance
        if (!left.isBalanced || !right.isBalanced) {
            return new ResultType(false, -1);
        }

        // root not balance
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
            return new ResultType(false, -1);
        }

        return new ResultType(true, Math.max(left.maxDepth, right.maxDepth) + 1);
    }
}
