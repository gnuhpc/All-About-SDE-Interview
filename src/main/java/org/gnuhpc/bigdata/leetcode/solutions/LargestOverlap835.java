package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestOverlap835 {
    public int largestOverlap(int[][] A, int[][] B) {
        int res = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = check(A, B, i, j);//以第二个matrix为基准进行移动
                int y = check(B, A, i, j);//倒过来表示另一个移动方向
                res = Math.max(res, Math.max(x, y));
            }
        }
        return res;
    }

    public int check(int[][] A, int[][] B, int r, int c) {
        int n = A.length;
        int res = 0;
        for (int i1 = r, i2 = 0; i1 < n && i2 < n; i1++, i2++) {
            for (int j1 = c, j2 = 0; j1 < n && j2 < n; j1++, j2++) {
                res += A[i1][j1] & B[i2][j2];
            }
        }
        return res;
    }

    /*
    这里的关键是如何把一个dy,dx hash成一个key
推倒过程如下
// To map a point in A to a point in B, we can use a vector [dy, dx] to represent the move
// We can use dy * (2cols) + dx to hash this vector
// (ay * 2cols + ax) - (by *2cols + bx) = (ay - by) * 2cols + (ax - bx) = dy * 2cols + dx
// dx (-cols, cols) dy(-rows, rows)
// so the hash factor should be (2 * cols - 1)
Time O(mn~m^2n^2)
     */

    public int largestOverlap2(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int hash = 2 * cols - 1;
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (A[y][x] == 1) {
                    listA.add(y * hash + x);
                }
                if (B[y][x] == 1) {
                    listB.add(y * hash + x);
                }
            }
        }
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : listA) {
            for (int b : listB) {
                int diff = a - b;
                int count = 1 + map.getOrDefault(diff, 0);
                max = Math.max(max, count);
                map.put(diff, count);
            }
        }
        return max;
    }
}
