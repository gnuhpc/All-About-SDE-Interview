package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;


//注意，题目没有说，但是从测试案例能看出如果没有子节点，则children是一个空列表不是null
public class Codec428 {

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

    /**
     * BFS  采用层序遍历来实现序列化和反序列化
     * time : O(n);
     * space : O(n);
     */
    //1,#,3,2,4,#,5,6,#,#,#,#,#,
    public String serialize(Node root) {
        if (root == null) return "";

        Queue<Node> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(",#,");
        q.add(root);

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node n : node.children) {
                sb.append(n.val).append(",");
                q.add(n);
            }

            sb.append("#,");
        }

        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0) return null;
        String[] s = data.split(",");

        Queue<Node> q = new LinkedList<>();
        Node root = new Node(Integer.parseInt(s[0]), new ArrayList<>());
        q.add(root);
        int i = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();
            i++;//跳过#
            while (!s[i].equals("#")) {
                Node c = new Node(Integer.parseInt(s[i]), new ArrayList<>());
                node.children.add(c);
                q.add(c);
                i++;//跳过#
            }
        }

        return root;
    }


    /*
    Method2: DFS
     */

    // Encodes a tree to a single string.
    // 1,3,3,2,5,0,6,0,2,0,4,0,
    // Encodes a tree to a single string.
    private static final String spliter = ",";
    private static final String NN = "X";

    public String serialize2(Node root) {
        StringBuilder sb = new StringBuilder();
        serializePreOrder(root, sb);
        return sb.toString();
    }

    private void serializePreOrder(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(root.val).append(spliter);
            sb.append(root.children.size()).append(spliter);
            for (Node node : root.children) {
                serializePreOrder(node, sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize2(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] strs = data.split(spliter);
        return deserializePreOrder(strs);
    }

    int pt = 0;
    private Node deserializePreOrder(String[] strs) {
        String val = strs[pt++];
        if (val.equals(NN)) {
            return null;
        } else {
            int size = Integer.parseInt(strs[pt++]);
            Node root = new Node(Integer.parseInt(val), new ArrayList<>(size));
            for (int i = 0; i < size; i++) {
                root.children.add(deserializePreOrder(strs));
            }
            return root;
        }
    }
}
