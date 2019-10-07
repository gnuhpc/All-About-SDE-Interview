package org.gnuhpc.bigdata.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/10/6
 */
public class Vector2D2512 {

    private int row, col;
    private int[][] vec;

    public Vector2D2512(int[][] v) {
        vec = v;
        row = 0;
        col = 0;
    }

    public int next() {
        hasNext();
        return vec[row][col++];
    }

    public boolean hasNext() {
        while (row < vec.length) {
            if (col < vec[row].length)
                return true;
            else {
                row++;
                col = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Vector2D2512 iterator = new Vector2D2512(new int[][]{
                {1, 2},
                {3},
                {4, 5}
        });

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
