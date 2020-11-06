package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class AdvantageCount870 {
    public int[] advantageCount(int[] a, int[] b) {
        int n = b.length;
        Arrays.sort(a);
        int[][] pair = new int[n][2];
        for (int i = 0; i < n; i ++)
            pair[i] = new int[]{b[i], i};//把下标保存下来
        Arrays.sort(pair, (x, y)->x[0] - y[0]);

        int[] res = new int[n];
        for (int i = 0, r = n - 1, l = 0; i < n; i ++)//r最大值，l标明最小值；
        {
            if (a[i] <= pair[l][0]) res[pair[r--][1]] = a[i];//要放到原数组对应的位置上
            else res[pair[l++][1]] = a[i];////要放到原数组对应的位置上
        }
        return res;
    }
}
