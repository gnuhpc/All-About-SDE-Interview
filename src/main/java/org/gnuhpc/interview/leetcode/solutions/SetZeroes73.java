package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

public class SetZeroes73 {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> columnSet = new HashSet<>();
        int M = matrix.length, N = matrix[0].length, i, j;
        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    columnSet.add(j);
                }
            }
        }

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                if (rowSet.contains(i) || columnSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
