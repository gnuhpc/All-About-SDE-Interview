package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class IsSameTree100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return (p.val == q.val && isSameTree(p.right, q.right) && isSameTree(p.left, q.left));
    }

    /*
    Method2: Preorder
     */

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        Deque<TreeNode> s1 = new LinkedList<>();
        Deque<TreeNode> s2 = new LinkedList<>();

        while ((p != null || !s1.isEmpty()) && (q != null || !s2.isEmpty())) {
            while (p != null || q != null) {
                if (p == null || q == null) return false;
                if (p.val != q.val) return false;
                else {
                    s1.push(p);
                    s2.push(q);

                    p = p.left;
                    q = q.left;
                }
            }

            p = s1.pop();
            p = p.right;

            q = s2.pop();
            q = q.right;
        }

        if (s1.isEmpty() && s2.isEmpty()) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
        }

        return true;
    }

    /*
    Method2: inorder
     */
    public boolean isSameTree3(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        Deque<TreeNode> s1 = new LinkedList<>();
        Deque<TreeNode> s2 = new LinkedList<>();

        while ((p != null || !s1.isEmpty()) && (q != null || !s2.isEmpty())) {
            while (p != null || q != null) {
                if (p == null || q == null) return false;
                s1.push(p);
                p = p.left;

                s2.push(q);
                q = q.left;
            }

            p = s1.pop();
            q = s2.pop();

            if (p == null || q == null) return false;
            if (p.val != q.val) return false;

            p = p.right;
            q = q.right;
        }

        return true;
    }

    /*
    Postorder
     */
    public boolean isSameTree4(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        Deque<TreeNode> s1 = new LinkedList<>();
        Deque<TreeNode> s2 = new LinkedList<>();

        while ((p != null || !s1.isEmpty()) && (q != null || !s2.isEmpty())) {
            while (p != null || q != null) {
                if (p == null || q == null) return false;
                if (p.val != q.val) return false;
                else {
                    s1.push(p);
                    s2.push(q);

                    p = p.right;
                    q = q.right;
                }
            }

            p = s1.pop();
            q = s2.pop();

            p = p.left;
            q = q.left;
        }

        if (s1.isEmpty() && s2.isEmpty()) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
        }

        return true;
    }

    @Test
    public void test() {
        TreeNode p = TreeCreator.createTreeByLevel(new Integer[]{2, 3, null});
        TreeNode q = TreeCreator.createTreeByLevel(new Integer[]{2, 3, 4});

        System.out.println(isSameTree2(p, q));
    }
}
