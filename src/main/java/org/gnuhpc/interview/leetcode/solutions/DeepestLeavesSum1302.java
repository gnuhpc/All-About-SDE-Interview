package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class DeepestLeavesSum1302 {
    public int deepestLeavesSum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int tmp = 0;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tmp += node.val;

                if(node.left != null) {
                    queue.offer(node.left);
                }

                if(node.right != null) {
                    queue.offer(node.right);
                }

                if(i==size-1 && queue.isEmpty()) sum = tmp;
            }
        }
        return sum;
    }
}
