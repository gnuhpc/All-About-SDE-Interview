package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class KthSmallest230 {
    /*
    Method 1: 递归 公共变量计数
     */

    private int res;
    private int count;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        if (count == k) return res;

        kthSmallest(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return res;
        }
        kthSmallest(root.right, k);

        return res;
    }


    /*
    Method2: 非递归计数 InOrder Traversal
     */

    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            //左子树
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (--k == 0) return p.val;
                p = p.right;
            }
        }

        return -1;
    }

}
