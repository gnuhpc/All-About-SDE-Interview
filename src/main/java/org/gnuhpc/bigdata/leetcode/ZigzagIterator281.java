package org.gnuhpc.bigdata.leetcode;

import java.util.List;

/**
 * Copyright gnuhpc 2019/10/6
 */
public class ZigzagIterator281 {
    List<Integer> l1;
    List<Integer> l2;
    int           i, j;

    public ZigzagIterator281(List<Integer> v1, List<Integer> v2) {
        l1 = v1;
        l2 = v2;
        i = 0;
        j = 0;
    }

    public int next() {
        if (!hasNext()) return -1;
        if (i <= j) {
            if (i < l1.size())
                return l1.get(i++);
            else {
                if (j < l2.size())
                    return l2.get(j++);
            }
        }
        else {
            if (j < l2.size())
                return l2.get(j++);
            else {
                if (i < l1.size())
                    return l1.get(i++);
            }
        }

        return -1;
    }

    public boolean hasNext() {
        return i < l1.size() || j < l2.size();
    }
}
