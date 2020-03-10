package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/2/29
 */
public class PathInZigZagTree1104 {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        int level = 1;
        int tail = 2;
        while (tail <= label) {
            tail *= 2;
            level++;
        }
        //System.out.println(level);
        tail = tail - 1;
        //System.out.println(tail);
        int head = tail / 2 + 1;
        //System.out.println(head);
        while (level > 0) {
            res.add(0, label);
            label = label % 2 == 0 ? label / 2 : (label - 1) / 2;
            tail = head - 1;
            head = tail / 2 + 1;
            label = tail - (label - head);
            level--;
        }
        return res;
    }
}
