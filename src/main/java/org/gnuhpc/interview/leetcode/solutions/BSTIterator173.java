package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;

public class BSTIterator173 {
    private Deque<TreeNode> s;

    public BSTIterator173(TreeNode root) {
        this.s = new LinkedList<>();
        dfs(root);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !s.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode n = s.poll();
        dfs(n.right);
        return n.val;
    }

    private void dfs(TreeNode root) {
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }
}
