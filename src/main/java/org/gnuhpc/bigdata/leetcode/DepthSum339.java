package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.NestedInteger;

import java.util.List;

/**
 * Copyright gnuhpc 2019/9/28
 */
public class DepthSum339 {
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    public int depthSum(List<NestedInteger> nestedList, int level) {
        if (nestedList == null || nestedList.size() == 0) return 0;

        int sum = 0;

        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) sum += level * ni.getInteger();
            else {
                sum += depthSum(ni.getList(), level + 1);
            }
        }

        return sum;
    }
}
