package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/4
 */
public class NumIslands2305 {

    private int[] ids;
    private int   rows, cols;
    private int[][] dr = new int[][]{
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            };

    //维护当前每个孤岛的parent id，也就是领头羊，领头羊的个数也就是孤岛区域的个数
    private Set<Integer> set = new HashSet<>();

    public List<Integer> numIslands2(int rows, int cols, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        this.rows = rows;
        this.cols = cols;
        ids = new int[rows * cols];
        //原来没有岛，都是水，因此都初始化为-1.
        //注意这个与number of island里面假设每个岛都是一个独立的岛，然后count去减合并数的思路相反
        Arrays.fill(ids, -1);
        for (int[] position : positions) {
            int curRow = position[0];
            int curCol = position[1];
            add(curRow, curCol);
            res.add(set.size());
        }
        return res;
    }

    private void add(int curRow, int curCol) {
        int idx = curRow * cols + curCol;
        //如果idx是一个新加入的岛
        if (ids[idx] == -1) {
            //先把新加入的点当做一个孤立的点，自己是自己的领头羊
            ids[idx] = idx; //初始化自己是自己的父亲
            set.add(idx);
        }

        //然后上下左右看看有没有其他的领头羊，如果有就进行连接，如果已经连接了就退出，不影响个数
        for (int[] d : dr) {
            int newRow = curRow + d[0];
            int newCol = curCol + d[1];
            int newIdx = newRow * cols + newCol;
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && ids[newIdx] != -1) {
                union(idx, newIdx);
            }
        }
    }

    private int find(int i) {
        if (i != ids[i]) {
            ids[i] = find(ids[i]);
        }
        return ids[i];
    }

    private void union(int i, int j) {
        int parentI = find(i);
        int parentJ = find(j);
        // 如果原来没有连接，现在可以连一下了
        if (parentI != parentJ) {
            set.remove(parentI);//注意如果建立关系要在set中维护这个独立点，既然连接了就要去掉这个set
        }
        ids[parentI] = parentJ;
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
