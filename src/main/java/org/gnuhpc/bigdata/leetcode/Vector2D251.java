package org.gnuhpc.bigdata.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/10/6
 */
public class Vector2D251 {
    Queue<Integer> q;

    public Vector2D251(int[][] v) {
        this.q = new LinkedList<>();
        for (int[] vv : v) {
            for (int n : vv) {
                q.offer(n);
            }
        }
    }

    public int next() {
        return q.poll();
    }

    public boolean hasNext() {
        return q.size() != 0;
    }

    public static void main(String[] args) {
        Vector2D251 iterator = new Vector2D251(new int[][]{
                {1, 2},
                {3},
                {4, 5}
        });

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
