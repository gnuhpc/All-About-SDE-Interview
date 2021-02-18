package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeLinkNode;
import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;

public class Connect116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node n = q.poll();

                if (i == size - 1) {
                    n.next = null;
                } else {
                    n.next = q.peek();
                }

                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
        }

        return root;
    }

    public Node connect2(Node root) {
        if (root == null || root.left == null) return root;
        //连接孩子结点，连接右孩子需要注意，如果root的next为null，则右孩子的next为null，否则为root.next的left
        Node left = root.left;
        Node right = root.right;
        left.next = right;
        if (root.next != null) {
            right.next = root.next.left;
        }
        connect2(left);
        connect2(right);
        return root;
    }

    public Node connect3(Node root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;
    }

    // 定义：输入两个节点，将它俩连接起来
    private void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序遍历位置 ****/
        // 将传入的两个节点连接
        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }


}
