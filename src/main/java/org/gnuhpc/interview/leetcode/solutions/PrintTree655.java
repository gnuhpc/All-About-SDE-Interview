package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PrintTree655 {
    public List<List<String>> printTree(TreeNode root) {
        int height = height(root);
        List<List<String>> res = new ArrayList<>();
        int size = (int) Math.pow(2, height) - 1;
        for (int i = 0; i < height; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < size; j++)
                cur.add("");
            res.add(cur);
        }
        dfs(root, 0, size - 1, res, 0);
        return res;
    }

    private int height(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private void dfs(TreeNode root, int left, int right,
                     List<List<String>> res, int depth) {
        if (left > right || root == null)
            return;
        int mid = (left + right) / 2;
        List<String> cur = res.get(depth);
        cur.set(mid, String.valueOf(root.val));
        dfs(root.right, mid + 1, right, res, depth + 1);
        dfs(root.left, left, mid - 1, res, depth + 1);
    }


    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{
                1, 2, 3, null, 4, null, null
        });

        System.out.println(height(root));
        System.out.println(printTree(root));
    }
}
