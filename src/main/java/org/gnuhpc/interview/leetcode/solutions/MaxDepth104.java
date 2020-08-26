package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaxDepth104 {
    /*
    Method1 : 非全局变量递归
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /*
    Method2: 全局变量
     */
    int max = 0;

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) return 1;

        helper(root, 1);
        return max;
    }

    private void helper(TreeNode root, int depth) {
        if (root == null) return;
        max = Math.max(max, depth);

        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }

    /*
    Method3: 用Stack 进行DFS
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            max = Math.max(temp, max);
            if (node.left != null) {
                stack.push(node.left);
                value.push(temp + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                value.push(temp + 1);
            }
        }
        return max;
    }

    /*
    Method4: BFS
     */
    public int maxDepth4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return level;
    }
}
