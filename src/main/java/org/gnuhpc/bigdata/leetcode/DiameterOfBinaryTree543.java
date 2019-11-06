package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
//下图为一个没有经过root的案例
//https://images2015.cnblogs.com/blog/695469/201706/695469-20170618214343478-1007869465.png
public class DiameterOfBinaryTree543 {
    int ans = 1; //ans是构成直径的路径上节点数，最少是1，也就是root本身
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return ans - 1 ; //减一是因为path = node count - 1
    }
    public int height(TreeNode node) {
        if (node == null) return 0;
        int left = height(node.left);
        int right = height(node.right);
        ans = Math.max(ans, left+right + 1); //记录节点数
        return Math.max(left, right) + 1;
    }
}
