package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/3/4
 */
public class MaxDepth559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

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
    Method2: 通过返回值迭代
     */

    public int maxDepth2(Node root) {
        if (root == null) return 0;
        List<Node> children = root.children;
        int max = 0;

        for (Node child : children) {
            int d = maxDepth2(child);
            if (d > max) max = d;
        }

        return 1 + max;
    }

}
