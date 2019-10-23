package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/10/19
 */
public class LongestConsecutive298 {

    /**
     * Method1 : Post-order DFS TODO
     */
    int ret = 0;

    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ret;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);

        if (root.left != null && root.left.val != root.val + 1) {
            leftSum = 0;
        }
        if (root.right != null && root.right.val != root.val + 1) {
            rightSum = 0;
        }

        ret = Math.max(ret, Math.max(leftSum, rightSum) + 1);
        return Math.max(leftSum, rightSum) + 1;

    }

    /*
    Method2: DFS TODO如何在下一层迭代中传入上一层的value
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
        return Math.max(Math.max(left, right), len);//max bt 3
        //len means length from root, need when single element or longest path start from root.
    }

    /*
    Method3: BFS
     */

    public int longestConsecutive333(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sizeQueue = new LinkedList<>();

        nodeQueue.offer(root);
        sizeQueue.offer(1); //弄了一个先进先出队列记录 TODO
        int max = 1;

        while (!nodeQueue.isEmpty()) {
            TreeNode head = nodeQueue.poll();
            int size = sizeQueue.poll();

            if (head.left != null) {
                int leftSize = size;
                if (head.val == head.left.val - 1) {
                    leftSize++;
                    max = Math.max(max, leftSize);
                }
                else {
                    leftSize = 1;
                }

                nodeQueue.offer(head.left);
                sizeQueue.offer(leftSize);
            }

            if (head.right != null) {
                int rightSize = size;
                if (head.val == head.right.val - 1) {
                    rightSize++;
                    max = Math.max(max, rightSize);
                }
                else {
                    rightSize = 1;
                }

                nodeQueue.offer(head.right);
                sizeQueue.offer(rightSize);
            }


        }

        return max;
    }

}
