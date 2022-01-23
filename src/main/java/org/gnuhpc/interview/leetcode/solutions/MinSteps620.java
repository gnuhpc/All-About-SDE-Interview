package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2021/9/19
 */
public class MinSteps620 {
    //一个当前长度，一个是复制或粘贴的长度。
    public int minSteps(int n) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0});
        int count = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                if (cur[0] == n) return count;
                if (cur[0] > n) continue;
                if (cur[0] != cur[1])
                    q.add(new int[]{cur[0], cur[0]});
                if (cur[1] != 0)
                    q.add(new int[]{cur[0] + cur[1], cur[1]});
            }
            count++;
        }
        return -1;
    }
}
