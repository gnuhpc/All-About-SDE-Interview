package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/10/23
 */
public class ClosestKValues272 {
    private Queue<Integer> pq;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        pq = new PriorityQueue<>(k);

        inorderTraverse(root, target, k);

        return new ArrayList<>(pq);
    }

    private void inorderTraverse(TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }

        inorderTraverse(root.left, target, k);

        if (pq.size() < k) {
            pq.offer(root.val);
        }
        else {
            if (Math.abs((double) root.val - target) < Math.abs((double) pq.peek() - target)) {
                pq.poll();
                pq.offer(root.val);
            }
        }

        inorderTraverse(root.right, target, k);
    }
}
