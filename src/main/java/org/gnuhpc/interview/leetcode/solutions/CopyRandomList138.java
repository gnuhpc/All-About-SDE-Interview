package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next, random;

    Node(int x) {
        this.val = x;
    }
}

public class CopyRandomList138 {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap();

        // loop 1. copy all the nodes
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);

    }

}
