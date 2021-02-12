package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/1/28
 */
public class BSTSequences0409 {
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            res.add(new ArrayList<>());
            return res;
        }
        int val = root.val;
        List<List<Integer>> left = BSTSequences(root.left),
                right = BSTSequences(root.right);
        for (List<Integer> x : left)
            for (List<Integer> y : right) {
                List<Integer> cur = new ArrayList<>();
                cur.add(val);
                dfs(res, cur, x, y, 0, 0);
            }
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> cur, List<Integer> x, List<Integer> y, int u, int v) {
        if (u == x.size() && v == y.size()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (u < x.size()) {
            cur.add(x.get(u));
            dfs(res, cur, x, y, u + 1, v);
            cur.remove(cur.size() - 1);
        }
        if (v < y.size()) {
            cur.add(y.get(v));
            dfs(res, cur, x, y, u, v + 1);
            cur.remove(cur.size() - 1);
        }
    }

    List<List<Integer>> listall = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> BSTSequences2(TreeNode root) {
        if (root == null) {
            listall.add(path);
            return listall;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        Order(queue);

        return listall;
    }

    public void Order(LinkedList<TreeNode> queue) {

        if (queue.size() == 0) {
            listall.add(new ArrayList<>(path));
            return;
        }

        int len = queue.size();
        for (int i = 0; i < len; i++) {

            TreeNode node = queue.pollFirst();
            int count = 0;
            if (node.left != null) {
                count++;
                queue.add(node.left);
            }
            if (node.right != null) {
                count++;
                queue.add(node.right);
            }

            path.add(node.val);

            Order(queue);

            path.remove(path.size() - 1);
            while (count-- > 0) {
                queue.pollLast();
            }
            queue.add(node);
        }
    }
}
