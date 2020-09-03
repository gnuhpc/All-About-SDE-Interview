package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/8/30
 */

/*
贪心，DFS会超时...
https://leetcode-cn.com/problems/reconstruct-a-2-row-binary-matrix/solution/java-tan-xin-suan-fa-xiang-jie-by-amanehayashi/
 */
public class ReconstructMatrix1253 {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        // up记录第0行可分配的1个数，lo记录第1行可分配的1个数
        int up = upper, lo = lower, sum = 0, len = colsum.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (colsum[i] == 2) {
                up--;
                lo--;
            } else if (colsum[i] == 1) {
                sum++;
            }
        }
        // 如果行列元素之和不相等，或行元素之和不够分配
        if (up + lo != sum || up < 0 || lo < 0) {
            return list;
        }
        List<Integer> upl = new ArrayList<>();
        List<Integer> lol = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (colsum[i] == 2) {
                upl.add(1);
                lol.add(1);
            } else if (colsum[i] == 0) {
                upl.add(0);
                lol.add(0);
            } else {
                // 先分配上
                if (up-- > 0) {
                    upl.add(1);
                    lol.add(0);
                }
                // 再分配下
                else {
                    lol.add(1);
                    upl.add(0);
                }
            }
        }
        list.add(upl);
        list.add(lol);
        return list;
    }
}
