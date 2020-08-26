package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/*
* 如果当前 find 正好在范围之内，那么把问题递归到它的左结点和右结点。
如果当前 find 不在范围内，比 L 小，那么 它和它的左子树 可以被抛弃了。
如果当前 find 不在范围内，比 R 大，那么 它和它的右子树 可以被抛弃了。
*
* */
public class TrimBST669 {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;

        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }
}
