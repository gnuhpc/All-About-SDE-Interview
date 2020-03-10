package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Copyright gnuhpc 19-7-10
 */
public class SumNumbers129 {


    //Method 1: Recursive
    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode n, int s) {
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s * 10 + n.val;
        return sum(n.left, s * 10 + n.val) + sum(n.right, s * 10 + n.val);
    }


    //Method 2: 纯DFS
    public int sumNumbers2(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        if (root.left == null && root.right == null)
            return root.val + sum * 10;
        sum = root.val + sum * 10;
        return helper(root.left, sum) + helper(root.right, sum);
    }


    //Method 3: BFS
    public int sumNumbers3(TreeNode root) {
        if (root == null) return 0;

        int sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();

            if (temp.left == null && temp.right == null)
                sum += temp.val;

            int num = (temp.val * 10);

            if (temp.left != null) {
                temp.left.val = temp.left.val + num;
                q.offer(temp.left);
            }

            if (temp.right != null) {
                temp.right.val = temp.right.val + num;
                q.offer(temp.right);
            }

        }

        return sum;
    }

    @Test
    public void test() {

        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{4, 9, 0, 5, 1});
        assertEquals(1026, sumNumbers(root));

        TreeNode root2 = TreeCreator.createTreeByLevel(new Integer[]{1, null, 5});
        assertEquals(15, sumNumbers(root2));
    }
}
