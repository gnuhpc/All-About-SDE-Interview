package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.*;

/**
 * Copyright gnuhpc 2020/2/29
 */
public class FindElements1261 {
    private Set<Integer> exists;


    public FindElements1261(TreeNode root) {
        exists = new HashSet<>();
        boolean allNull = true;

        Queue<TreeNode> q = new LinkedList<>();

        root.val = 0;
        exists.add(0);
        q.add(root);

        while (!q.isEmpty()) {
            allNull = true;
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node != null) {


                    if (node.left != null) {
                        allNull = false;
                        node.left.val = node.val * 2 + 1;
                        exists.add(node.left.val);
                    }

                    if (node.right != null) {
                        allNull = false;
                        node.right.val = node.val * 2 + 2;
                        exists.add(node.right.val);
                    }
                    q.offer(node.left);
                    q.offer(node.right);
                    //System.out.print(node.val + " ");
                } else {
                    q.offer(null);
                    q.offer(null);
                    //System.out.print("null ");
                }
            }

            if (allNull) break;
            //System.out.println();
        }

    }

    public boolean find(int target) {
        return exists.contains(target);
    }

}
