package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 2019/10/1
 */
public class KSmallestPairs373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) return res;
        if (nums1.length * nums2.length <= k) {
            for (int n1 : nums1) {
                for (int n2 : nums2) {
                    res.add(new ArrayList<>(Arrays.asList(n1, n2)));
                }
            }

            return res;
        }

        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //pq的大小小于k的时候无脑放入
                if (pq.size() < k) pq.offer(new Tuple(i, j, nums1[i] + nums2[j]));
                    //如果已经有k个了.那么就往外踢
                else {
                    int topValue = pq.peek().val;
                    if (topValue > nums1[i] + nums2[j]) {
                        pq.poll();
                        pq.offer(new Tuple(i, j, nums1[i] + nums2[j]));
                    }
                }
            }
        }

        while (!pq.isEmpty()) {
            Tuple tuple = pq.poll();
            res.add(new ArrayList<>(Arrays.asList(nums1[tuple.x], nums2[tuple.y])));
        }

        return res;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return that.val - this.val;
        }
    }
}


