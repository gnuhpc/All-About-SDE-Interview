package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MergeTrees617 {
    /*
    Method 1: 递归版本
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            //两个均不为空的情况
            TreeNode node = new TreeNode(t1.val + t2.val);
            node.left = mergeTrees(t1.left, t2.left);
            node.right = mergeTrees(t1.right, t2.right);
            return node;
        } else {
            //有为空的就返回另一个
            return t1 == null ? t2 : t1;
        }
    }

    /*
    Method 2: 非递归版本，层次遍历
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(t1);
        q2.offer(t2);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            TreeNode node_1 = q1.poll();
            TreeNode node_2 = q2.poll();
            if (node_1 == null || node_2 == null) continue;
            node_1.val += node_2.val;
            if (node_1.left == null) node_1.left = node_2.left;
            else {
                q1.offer(node_1.left);
                q2.offer(node_2.left);
            }
            if (node_1.right == null) node_1.right = node_2.right;
            else {
                q1.offer(node_1.right);
                q2.offer(node_2.right);
            }


        }
        return t1;
    }

}
