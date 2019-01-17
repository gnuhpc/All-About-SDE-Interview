package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class LowestCommonAncestor235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //分属在两边的，那么root一定是lca
        if ((p.val > root.val && q.val < root.val) ||
                (p.val < root.val && q.val > root.val))
            return root;

        //其中一个节点是root的，直接返回该节点
        if (root.val == p.val) return p;
        if (root.val == q.val) return q;

        //都在一边的，递归查找
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);

        return null;
    }


}
