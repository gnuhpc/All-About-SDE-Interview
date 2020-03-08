package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

public class Connect116 {
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        Queue<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeLinkNode n = q.poll();

                if (i == size - 1) {
                    n.next = null;
                }
                else {
                    n.next = q.peek();
                }

                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
        }

    }

    public void connectRecursive(TreeLinkNode root) {
        if (root == null) return;
        if (root.left != null) root.left.next = root.right;
        if (root.right != null) {
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            else {
                root.right = null;
            }
        }

        connectRecursive(root.left);
        connectRecursive(root.right);

    }
}
