package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/3/4
 */
public class MaxDepth559 {

    /*
    Method1: 全局变量递归
     */
    private int max = 0;

    public int maxDepth(Node root) {
        helper(root, 1);
        return max;
    }

    private void helper(Node node, int depth) {
        if (node == null)
            return;
        max = Math.max(max, depth);
        for (Node n : node.children) {
            helper(n, depth + 1);
        }
    }

    /*
    Method2:
     */

    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> heights = new LinkedList<>();
            for (Node item : root.children) {
                heights.add(maxDepth2(item));
            }
            return Collections.max(heights) + 1;
        }
    }

}
