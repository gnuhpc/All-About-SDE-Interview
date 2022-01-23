package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/1
 */
/*
让A数组中所有元素和B数组中的第一个元素B[0]配对，注意这里我们加入的二元组是数组的索引，形成A.length对二元组，分别将其放入极小堆中
从极小堆中取出一个元素(i,j)（也就是最小的二元组），放入结果数组中，然后得到这个元组在B数组中的索引位置并加一，得到新的二元组（i,j+1），将其放入极小堆中。
 */
public class KSmallestPairs373 {
    class Node {
        public int idx1;
        public int idx2;

        public Node(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] A, int[] B, int k) {
        if (A == null || B == null || A.length == 0 || B.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        int lenA = A.length;
        int lenB = B.length;

        // 小根堆
        Queue<Node> queue = new PriorityQueue<>((v1, v2) -> A[v1.idx1] + B[v1.idx2] - A[v2.idx1] - B[v2.idx2]);

        for (int i = 0; i < lenA; i++) {
            queue.add(new Node(i, 0));
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            Node node = queue.poll();
            ans.add(Arrays.asList(A[node.idx1], B[node.idx2]));
            if (node.idx2 + 1 < lenB) {
                queue.add(new Node(node.idx1, node.idx2 + 1));
            }
        }

        return ans;
    }
}


