package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2021/4/4
 */
public class LowestCommonAncestor1650 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    //Method1: 两边一起往上
    public Node lowestCommonAncestor(Node p, Node q) {
        if (p.val == q.val) {
            return p;
        }
        Node a = p;
        Node b = q;
        while (true) {
            a = a.parent != null ? a.parent : q;
            b = b.parent != null ? b.parent : p;
            if (a.val == b.val) {
                return a;
            }
        }
    }

    //Method2： 一边爬到顶记录下
    public Node lowestCommonAncestor2(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        Node c = p;
        while (c != null) {
            set.add(c);
            c = c.parent;
        }

        c = q;

        while (!set.contains(c)) {
            c = c.parent;
        }

        return c;
    }
}
