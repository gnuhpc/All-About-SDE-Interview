package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 2019/12/15
 */
public class CheckEqualTree663 {
    public boolean checkEqualTree(TreeNode root) {
        if (root == null) return false;
        int sum = getSum(root);
        //如果整棵树元素和为奇数则不能均分
        if (sum % 2 != 0) return false;
        return helper(root, sum / 2);
    }

    private boolean helper(TreeNode root, int sum) {
        if (root == null) return false;
        //比较左子树
        if (root.left != null && getSum(root.left) == sum) return true;
        //比较右子树
        if (root.right != null && getSum(root.right) == sum) return true;
        return helper(root.left, sum) || helper(root.right, sum);
    }

    //获取树的元素和
    private int getSum(TreeNode root) {
        if (root == null) return 0;
        return getSum(root.left) + getSum(root.right) + root.val;
    }

}
