package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Copyright gnuhpc 19-7-10
 */
public class SumNumbers129 {

    //Method 1: 纯DFS
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        if (root.left == null && root.right == null)
            return root.val + sum * 10;
        sum = root.val + sum * 10;
        return helper(root.left, sum) + helper(root.right, sum);
    }


    //Method 2: BFS
    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.add(root);
        numQueue.add(root.val);
        while (!nodeQueue.isEmpty()) {
            // 同时维护两个队列
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            // 如果该节点是叶子节点，加到res中
            if (node.left == null && node.right == null) {
                res += num;
            } else {
                // 左节点不为空时，左节点进入队列，左节点对应的值是当前节点num*10+node.left.val
                if (node.left != null) {
                    nodeQueue.add(node.left);
                    numQueue.add(num * 10 + node.left.val);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                    numQueue.add(num * 10 + node.right.val);
                }
            }
        }
        return res;
    }

    @Test
    public void test() {

        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{4, 9, 0, 5, 1});
        assertEquals(1026, sumNumbers(root));

        TreeNode root2 = TreeCreator.createTreeByLevel(new Integer[]{1, null, 5});
        assertEquals(15, sumNumbers(root2));
    }
}
