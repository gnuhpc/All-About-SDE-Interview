package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
//下图为一个没有经过root的案例
//https://images2015.cnblogs.com/blog/695469/201706/695469-20170618214343478-1007869465.png
/*
最大的直径可能有三种情况：
1.在左子树内部
2.在右子树内部
3.在穿过左子树，根节点，右子树的一条路径中

设计一个递归函数，返回以该节点为根节点，向下走的最长路径
知道这个值以后
以某个节点为根节点的最长直径就是，该节点左子树向下走的最长路径 ，再加上该节点右子树向下走的最长路径
我们用一个全局变量记录这个最大值，不断更新

 */
public class DiameterOfBinaryTree543 {
    /*
    Method1
     */

    private int max = 0;

    public int diameterOfBinaryTree1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        max = Math.max(max, (left + right));
        diameterOfBinaryTree1(root.left);
        diameterOfBinaryTree1(root.right);
        return max;

    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    /*
    Method2: 造成效率不高的原因主要是这么写其实进行了两次遍历。
其实我们在求每个节点的高度的时候，顺带就可以求出其直径
     */
    public int diameterOfBinaryTree2(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
