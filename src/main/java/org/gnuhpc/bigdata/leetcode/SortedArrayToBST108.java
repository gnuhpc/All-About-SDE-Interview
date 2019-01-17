package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

// 二分生成的BST是高度平衡的BST
public class SortedArrayToBST108 {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode head = helper(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helper(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }

    /*
    Method2 : dfs
     */

    private static class MyNode {
        TreeNode node;
        int leftBound, rightBound;
        public MyNode(TreeNode node, int left, int right) {
            this.node = node;
            this.leftBound = left;
            this.rightBound = right;
        }
    }

    // Iterative version
    public static TreeNode sortedArrayToBST_Iterative_DFS(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        TreeNode root = new TreeNode(0); // We start with zero as default
        Deque<MyNode> stack = new LinkedList<>();

        MyNode node = new MyNode(root, 0, nums.length - 1);
        stack.push(node);

        while (!stack.isEmpty()) {
            MyNode cur = stack.pop();
            int mid = cur.leftBound + (cur.rightBound - cur.leftBound) / 2;
            cur.node.val = nums[mid];

            if (cur.leftBound < mid) {
                cur.node.left = new TreeNode(0);
                stack.push(new MyNode(cur.node.left, cur.leftBound, mid - 1));
            }

            if (cur.rightBound > mid) {
                cur.node.right = new TreeNode(0);
                stack.push(new MyNode(cur.node.right, mid + 1, cur.rightBound));
            }
        }
        return root;
    }
}
