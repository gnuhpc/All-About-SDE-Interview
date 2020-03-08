package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/*
最长的路径可能有三种情况：
1.在左子树内部
2.在右子树内部
3.在穿过左子树，根节点，右子树的一条路径中

设计一个递归函数，返回以该节点为根节点，向下走的最长同值路径
知道这个值以后
以某个节点为根节点的最长同值路径就是，
如果该节点的值等于其左子树的值，则最长同值路径要加上左子树的最长同值路径，如果不等，左子树的路径为0
如果该节点的值等于其右子树的值，则最长同值路径要加上右子树的最长同值路径，如果不等，右子树的路径为0

我们用一个全局变量记录这个最大值，不断更新

作者：a380922457
链接：https://leetcode-cn.com/problems/longest-univalue-path/solution/jian-dan-yi-dong-ban-by-a380922457-7/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

public class LongestUnivaluePath687 {
    int res = 0;

    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return res;
    }

    int helper(TreeNode root) {
        if (root == null) return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        left = (root.left != null && root.left.val == root.val) ? left + 1 : 0;
        right = (root.right != null && root.right.val == root.val) ? right + 1 : 0;

        res = Math.max(res, left + right);
        return Math.max(left, right);
    }
}
