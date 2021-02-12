package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2021/1/30
 */
public class BSTIterator1586 {
    //始终指向双向链表头节点
    private final TreeNode dummyNode = new TreeNode(Integer.MIN_VALUE);

    //迭代器当前所处节点
    private TreeNode current;

    //用于原地构造双向链表
    private TreeNode prev;

    public BSTIterator1586(TreeNode root) {
        prev = dummyNode;

        //中序遍历原地构造双向链表
        if (root != null) {
            inorder(root);
        }

        current = dummyNode;


    }

    public boolean hasNext() {
        return current.right != null;
    }

    public int next() {
        current = current.right;
        return current.val;
    }

    public boolean hasPrev() {
        return current.left != null && current.left != dummyNode;
    }

    public int prev() {
        current = current.left;
        return current.val;
    }

    //原地构造双向链表
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;
        inorder(root.right);

    }
}
