package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/10/19
 */
public class LongestConsecutive298 {

    /**
     * Method1 : Post-order DFS
     */
    int res = 0;

    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftLength = dfs(root.left);
        int rightLength = dfs(root.right);

        if (root.left != null && root.left.val != root.val + 1) {
            leftLength = 0;
        }
        if (root.right != null && root.right.val != root.val + 1) {
            rightLength = 0;
        }

        res = Math.max(res, Math.max(leftLength, rightLength) + 1);
        return Math.max(leftLength, rightLength) + 1;
    }

    /*
    Method2: pre order 如何在下一层迭代中传入上一层的value
     */

    public int longestConsecutive2(TreeNode root) {
        if (root == null) return 0;
        return helper(root, 0, root.val);//node,len,last value
    }

    public int helper(TreeNode root, int len, int val) {
        if (root == null) return 0;
        if (root.val == val + 1) len++;
        else len = 1;
        int left = helper(root.left, len, root.val);
        int right = helper(root.right, len, root.val);
        return Math.max(Math.max(left, right), len);//max
        //len means length from root, need when single element or longest path start from root.
    }

    /*
    Method3: BFS
     */

    public int longestConsecutive3(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sizeQueue = new LinkedList<>();

        nodeQueue.offer(root);
        sizeQueue.offer(1); //弄了一个先进先出队列记录
        int max = 1;

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int size = sizeQueue.poll();

            if (node.left != null) {
                int leftSize = size;
                if (node.val == node.left.val - 1) {
                    leftSize++;
                    max = Math.max(max, leftSize);
                }
                else {
                    leftSize = 1;
                }

                nodeQueue.offer(node.left);
                sizeQueue.offer(leftSize);
            }

            if (node.right != null) {
                int rightSize = size;
                if (node.val == node.right.val - 1) {
                    rightSize++;
                    max = Math.max(max, rightSize);
                }
                else {
                    rightSize = 1;
                }

                nodeQueue.offer(node.right);
                sizeQueue.offer(rightSize);
            }
        }

        return max;
    }

}
