package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/10/12
 */
public class IsEvenOddTree1609 {
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            int pre;
            int flag = level % 2;
            if (flag == 0) { // 偶数层，奇数递增
                pre = Integer.MIN_VALUE;
            } else {
                pre = Integer.MAX_VALUE;
            }
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.val % 2 == flag) return false;
                if (flag == 0 && pre >= temp.val) return false;
                if (flag == 1 && pre <= temp.val) return false;
                pre = temp.val;
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
        }

        return true;
    }
}
