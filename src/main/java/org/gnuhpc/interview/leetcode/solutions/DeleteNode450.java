package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

public class DeleteNode450 {
    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            //情况 1：A 恰好是末端节点，两个子节点都为空，那么它可以当场去世了。
            if (root.left == null && root.right == null)
                return null;
            // 情况 2：A 只有一个非空子节点，那么它要让这个孩子接替自己的位置。
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 处理情况 3 A 有两个子节点，麻烦了，为了不破坏 BST 的性质，
            // A 必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己。我们以第二种方式讲解。
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val == key) {
            //System.out.println("root.val"+root.val);
            // 处理删除逻辑
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode cur = getMin(root.right);
            cur.right = deleteNode(root.right, cur.val);
            cur.left = root.left;
//            root.left = root.right = null;
            return cur;
        }

        root.left = deleteNode(root.left, key);
        root.right = deleteNode(root.right, key);

        return root;

    }

}
