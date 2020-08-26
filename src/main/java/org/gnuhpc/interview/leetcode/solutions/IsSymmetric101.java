package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.*;

public class IsSymmetric101 {
    /*
    Method 1: Recursion
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null)
            return left == right;
        if (left.val != right.val)
            return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }


    /*
    Method 2: iteration inorder
     */

    public boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        Deque<TreeNode> s1 = new LinkedList<>();
        Deque<TreeNode> s2 = new LinkedList<>();

        TreeNode p = root;
        TreeNode q = root;

        while ((p != null || !s1.isEmpty()) && (q != null || !s2.isEmpty())) {
            while (p != null || q != null) {
                if (p == null || q == null) return false;

                if (p.val != q.val) return false;
                else {
                    s1.push(p);
                    p = p.left;

                    s2.push(q);
                    q = q.right;
                }
            }

            p = s1.pop();
            p = p.right;

            q = s2.pop();
            q = q.left;
        }

        if (s1.isEmpty() && s2.isEmpty()) {
            if (p == null && q == null) return true;
            return p != null && q != null;
        }

        return true;
    }

    /*
    Method 3:BFS
     */

    public boolean isSymmetric3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return true;
        q.add(root.left);
        q.add(root.right);
        while (q.size() > 1) {
            TreeNode left = q.poll(), right = q.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }


    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{2, 3, 3, 4, 5, null, 4});

        System.out.println(isSymmetric2(root));
    }
}
