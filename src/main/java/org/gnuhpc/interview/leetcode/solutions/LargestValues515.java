package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestValues515 {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        List<Integer> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (n.val > max) max = n.val;
                if (n.right != null) q.offer(n.right);
                if (n.left != null) q.offer(n.left);
            }

            res.add(max);
        }

        return res;
    }


    List<Integer> result;

    public List<Integer> largestValuesRecusivly(TreeNode root) {
        result = new ArrayList<>();
        l(root, 0);
        return result;
    }

    private void l(TreeNode root, int i) {
        if (root == null) return;
        if (result.size() == i) {
            result.add(root.val);
        } else if (result.get(i) < root.val) {
            result.set(i, root.val);
        }
        l(root.left, i + 1);
        l(root.right, i + 1);
    }
}
