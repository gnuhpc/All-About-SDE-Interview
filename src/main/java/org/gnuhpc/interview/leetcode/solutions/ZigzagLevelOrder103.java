package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.*;

public class ZigzagLevelOrder103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        boolean isQueue = true;
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Deque<Integer> l = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (isQueue) {
                    l.addFirst(n.val);
                } else {
                    l.addLast(n.val);
                }

                if (n.right != null) q.offer(n.right);
                if (n.left != null) q.offer(n.left);
            }

            res.add(new ArrayList<>(l));
            isQueue = !isQueue;
        }

        return res;
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{3, 9, 20, null, null, 15, 7});

        zigzagLevelOrder(root);
    }
}
