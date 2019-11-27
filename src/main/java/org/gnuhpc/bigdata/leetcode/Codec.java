package org.gnuhpc.bigdata.leetcode;

import java.util.List;


public class Codec {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Node root = new Node();

        return root;
    }
}
