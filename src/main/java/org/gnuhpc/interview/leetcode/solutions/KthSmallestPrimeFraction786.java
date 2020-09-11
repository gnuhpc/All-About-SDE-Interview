package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/9/6
 */
public class KthSmallestPrimeFraction786 {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int len = A.length;
        int nodeCount = len * (len - 1) / 2;
        if (K > nodeCount) {
            return new int[0];
        }
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.val.compareTo(o1.val));
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                Node node = new Node(A[i], A[j]);
                if (queue.size() < K) {
                    queue.offer(node);
                } else if (queue.peek().val > node.val) {
                    queue.poll();
                    queue.add(node);
                }
            }
        }

        Node rs = queue.peek();
        return new int[]{rs.fenzi, rs.fenmu};
    }

    class Node {
        Double val;
        int fenzi;
        int fenmu;

        Node(int fenzi, int fenmu) {
            this.fenzi = fenzi;
            this.fenmu = fenmu;
            val = (double) fenzi / (double) fenmu;
        }
    }

}
