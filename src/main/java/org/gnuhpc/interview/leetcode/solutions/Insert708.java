package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/11
 */

public class Insert708 {
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {

        if (head == null) {
            Node result = new Node(insertVal);
            result.next = result;
            return result;
        }

        Node node = head;
        while (node.next != head) {
            if (node.val <= insertVal && insertVal <= node.next.val) {
                // 插入非最值
                break;
            } else if (node.val <= insertVal && node.next.val < insertVal && node.val > node.next.val) {
                // 插入一个最大值
                break;
            } else if (node.val > insertVal && node.next.val >= insertVal && node.val > node.next.val) {
                // 插入一个最小值
                break;
            } else {
                node = node.next;
            }
        }

        Node inserted = new Node(insertVal);
        inserted.next = node.next;
        node.next = inserted;
        return head;
    }
}
