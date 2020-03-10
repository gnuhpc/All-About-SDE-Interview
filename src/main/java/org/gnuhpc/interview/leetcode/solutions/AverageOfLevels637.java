package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevels637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) return res;

        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> subRes = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                subRes.add(n.val);
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }

            double avg = subRes.parallelStream().mapToDouble(a -> a).average().getAsDouble();
            res.add(avg);
        }

        return res;
    }
}
