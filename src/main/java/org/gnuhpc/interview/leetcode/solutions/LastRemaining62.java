package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/3/31
 */
public class LastRemaining62 {
    public int lastRemaining(int n, int m) {
        //基于ArrayList的模拟链表删除
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }
}
