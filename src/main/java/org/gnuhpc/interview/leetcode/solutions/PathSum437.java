package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 19-7-10
 */

// Answer from Jiuzhang. Double Recursions. 重点在于如何划分递归的部分
public class PathSum437 {
    //pathSum: 返回以root为根的树中，所有路径和为sum的路径个数（以root开头，不以root开头）
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        //由三部分组成：不以root开头的路径（1. 递归左子树结果 2.递归右子树结果 ）
        //3. 必须以root开头的路径
        return pathSum(root.left, sum) + pathSum(root.right, sum) + findPath(root, sum);
    }

    //统计必须从root出发，路径和为sum的所有路径个数
    public int findPath(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = 0;//由三部分组成
        if (sum == root.val) { //已经找到了以root结尾的路径。累加进res
            res += 1;
        }
        //因为后序路径可能存在类似于加n减n的情况，所以需要继续递归寻找左右子树
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

}
