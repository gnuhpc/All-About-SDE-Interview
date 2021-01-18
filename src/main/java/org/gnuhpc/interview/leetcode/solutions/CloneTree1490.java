package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/10/12
 */
public class CloneTree1490 {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    public Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }
        // 复制当前节点
        Node cloneRoot = new Node(root.val);
        for (Node child : root.children) {
            // 进入孩子节点中，复制孩子节点
            cloneRoot.children.add(cloneTree(child));
        }
        return cloneRoot;
    }
}
