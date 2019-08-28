package org.gnuhpc.bigdata.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Connect117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if (root==null) return root;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node n = q.poll();

                if (i==size-1){
                    n.next = null;
                } else {
                    n.next = q.peek();
                }

                if (n.left!=null) q.offer(n.left);
                if (n.right!=null) q.offer(n.right);
            }
        }

        return root;
    }
}
