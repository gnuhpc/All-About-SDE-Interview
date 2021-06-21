package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/4/27
 */
public class OrderedStream1656 {
    private int ptr;
    private final String[] arr;

    public OrderedStream1656(int n) {
        this.ptr = 0;
        this.arr = new String[n];
    }

    public List<String> insert(int idKey, String value) {
        List<String> res = new LinkedList<>();
        int idx = idKey - 1;

        arr[idx] = value;
        if (idx > ptr) return res;
        else {
            int i = idx;
            for (; i < arr.length && arr[i] != null; i++) {
                res.add(arr[i]);
            }
            ptr = i;

            return res;
        }
    }
}
