package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/11/8
 */
public class CBTInserter919 {
    TreeNode root;
    Queue<TreeNode> queue;

    public CBTInserter919(TreeNode root) {
        queue = new LinkedList<>();
        this.root = root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.peek();
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            } else {
                break;
            }
            queue.poll();
        }
    }

    public int insert(int v) {
        assert !queue.isEmpty();
        TreeNode t = new TreeNode(v);
        TreeNode temp = queue.peek();
        if (temp.left == null) {
            temp.left = t;
        } else {
            temp.right = t;
            queue.poll();
        }
        queue.offer(t);
        return temp.val;
    }

    public TreeNode get_root() {
        return root;
    }

}
