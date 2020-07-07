package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class HasPathSum112 {
    /*
    Method : recursive
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null && sum - root.val == 0) {
            System.out.println(root.val);
            return true;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /*
    Method2 : BFS
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }

    public boolean hasPathSum3(TreeNode root, int sum) {
        // iteration method
        if (root == null) {
            return false;
        }
        Stack<TreeNode> path = new Stack<>();
        Stack<Integer> sub = new Stack<>();
        path.push(root);
        sub.push(root.val);
        while (!path.isEmpty()) {
            TreeNode temp = path.pop();
            int tempVal = sub.pop();
            if (temp.left == null && temp.right == null) {
                if (tempVal == sum) return true;
            } else {
                if (temp.left != null) {
                    path.push(temp.left);
                    sub.push(temp.left.val + tempVal);
                }
                if (temp.right != null) {
                    path.push(temp.right);
                    sub.push(temp.right.val + tempVal);
                }
            }
        }
        return false;
    }
}
