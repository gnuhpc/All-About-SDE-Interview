package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/4/8
 */
public class MovingCount13 {
    //统计到达的数量
    int counts, n, m, k;

    public int movingCount(int m, int n, int k) {
        //辅助数组 用来标记是否统计过
        this.m = m;
        this.n = n;
        this.k = k;
        int[][] visited = new int[m][n];
        //从 0,0位置开始统计
        helper(visited, 0, 0);
        return counts;
    }

    /**
     * 传入i,j两点 判断当前点是否符合规则 符合规则下继续对下右两个方向递归判断
     */
    private void helper(int[][] visited, int i, int j) {
        if (isValid(visited, i, j)) {
            counts++;
            visited[i][j] = 1;
            helper(visited, i + 1, j);
            helper(visited, i, j + 1);
        }
    }

    private boolean isValid(int[][] visited, int i, int j) {
        return (i < m && j < n && visited[i][j] != 1 && (indexSum(i) + indexSum(j)) <= k);
    }

    /**
     * 根据传入的数 求出各位上的数字累加和
     */
    private int indexSum(int index) {
        int sum = index % 10;
        int tmp = index / 10;
        while (tmp > 0) {
            sum += tmp % 10;
            tmp /= 10;
        }
        return sum;
    }
}
