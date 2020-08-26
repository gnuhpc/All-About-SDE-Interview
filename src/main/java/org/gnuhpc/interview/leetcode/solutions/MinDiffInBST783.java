package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

public class MinDiffInBST783 {
    Integer res = Integer.MAX_VALUE, pre = null;

    //实际上是一个中序遍历
    public int minDiffInBST(TreeNode root) {
        if (root.left != null) {
            minDiffInBST(root.left);
        }
        //如果前边已经记录的话，此时计算此节点值与上一个记录的节点值的差值，与res取较小的一个
        if (pre != null) res = Math.min(res, root.val - pre);
        pre = root.val;
        if (root.right != null) {
            minDiffInBST(root.right);
        }
        return res;
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        minDiffInBST(root);
    }
}
