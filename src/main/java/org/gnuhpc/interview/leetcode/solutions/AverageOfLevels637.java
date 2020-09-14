package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

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

            int size = q.size();
            int count = 0;
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                count++;
                sum += n.val;
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            res.add(sum / count);
        }

        return res;
    }

}
