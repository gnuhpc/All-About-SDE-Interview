package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) return res;

        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                temp.add(n.val);
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }

            res.add(temp);
        }

        return res;
    }

    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, res, 0);
        return res;
    }

    public void levelOrder(TreeNode root, List<List<Integer>> res, int level) {
        if (root != null) {
            if (res.size() > level) {
                List<Integer> list = res.get(level);
                list.add(root.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(root.val);
                res.add(list);
            }
            level++;
            levelOrder(root.left, res, level);
            levelOrder(root.right, res, level);
        }
    }

}
