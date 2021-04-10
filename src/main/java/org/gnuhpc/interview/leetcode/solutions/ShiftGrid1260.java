package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/3/26
 */
public class ShiftGrid1260 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> list = new ArrayList<>();
        // 先用一个一维列表来保存二维数组的结果
        for (int[] ints : grid) {
            for (int a : ints) {
                list.add(a);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        // 取模
        k = k % list.size();
        // 将位置摆好
        for (int i = 0; i < k; i++) {
            list.add(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
        }
        // 重新转化为二维列表
        int r = 0;
        for (int i = 0; i < m; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                temp.add(list.get(r++));
            }
            res.add(temp);
        }
        return res;
    }
}
