package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.unionfind.QuickUnion;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/4
 */
public class NumIslands2305 {

    private int[] POINTS = {0, -1, 0, 1, 0};

    public List<Integer> numIslands2(int rows, int cols, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        // store all the ids element
        Set<Integer> set = new HashSet<>();
        int[] ids = new int[rows * cols];
        Arrays.fill(ids, -1);
        for (int[] position : positions) {
            int curRow = position[0];
            int curCol = position[1];
            add(set, ids, curRow, curCol, rows, cols);
            res.add(set.size());
        }
        return res;
    }

    private void add(Set<Integer> set, int[] ids, int curRow, int curCol, int rows, int cols) {
        int idx = curRow * cols + curCol;
        ids[idx] = idx;
        set.add(idx);
        for (int i = 0; i < POINTS.length - 1; i++) {
            int newRow = curRow + POINTS[i];
            int newCol = curCol + POINTS[i + 1];
            int newIdx = newRow * cols + newCol;
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && ids[newIdx] != -1) {
                union(set, ids, idx, newIdx);
            }
        }
    }

    private int find(int[] ids, int i) {
        if (ids[i] == i) {
            return i;
        }
        ids[i] = find(ids, ids[i]);
        return ids[i];
    }

    private void union(Set<Integer> set, int[] ids, int i, int j) {
        int parentI = find(ids, i);
        int parentJ = find(ids, j);
        if (parentI != parentJ) {
            ids[parentI] = parentJ;
            set.remove(parentI);//注意如果建立关系要在set中维护的这个独立点
        }
    }


    @Test
    public void test() {
//        System.out.println(numIslands2(3, 3, new int[][]{
//                {0, 0}, {0, 1}, {1, 2}, {2, 1}
//        }));
//        System.out.println(numIslands2(3, 3, new int[][]{
//                {0, 1}, {1, 2}, {2, 1}, {1, 0}, {0, 2}, {0, 0}, {1, 1}
//        }));

        System.out.println(numIslands2(3, 3, new int[][]{
                {0, 0}, {0, 1}, {1, 2}, {2, 1}, {1, 0}, {0, 0}, {2, 2}, {1, 2}, {1, 1}, {0, 1}
        }));
    }
}
