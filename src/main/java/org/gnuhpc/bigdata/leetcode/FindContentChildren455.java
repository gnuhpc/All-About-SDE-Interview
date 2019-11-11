package org.gnuhpc.bigdata.leetcode;

import org.apache.derby.impl.sql.execute.AggregatorInfoList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 2019/9/21
 */
public class FindContentChildren455 {
    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int res = 0;

        for (int i = g.length - 1; i >= 0; i--) {
            int gi = g[i];
            for (int j = s.length - 1; j >= 0; j--) {
                if (s[j] >= gi) {
                    res++;
                    s[j] = -1;
                }
            }
        }

        return res;
    }
}
