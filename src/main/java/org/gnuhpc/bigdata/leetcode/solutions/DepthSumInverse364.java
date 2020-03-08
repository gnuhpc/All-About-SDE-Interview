package org.gnuhpc.bigdata.leetcode.solutions;

import com.google.inject.internal.cglib.core.$VisibilityPredicate;
import org.gnuhpc.bigdata.leetcode.utils.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/9/28
 */
public class DepthSumInverse364 {
    /*
    Method1 : DFS
     */

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int level = getDepth(nestedList);

        return depthSumInverse(nestedList, level);
    }

    public int depthSumInverse(List<NestedInteger> nestedList, int level) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += level * ni.getInteger();
            }
            else {
                sum += depthSumInverse(ni.getList(), level - 1);
            }
        }

        return sum;
    }

    private int getDepth(List<NestedInteger> nestedList) {
        int max = Integer.MIN_VALUE;

        for (NestedInteger ni : nestedList) {
            if (!ni.isInteger()) max = Math.max(max, 1 + getDepth(ni.getList()));
        }

        if (max == Integer.MIN_VALUE) {
            return 1;
        }

        return max;
    }

    /*
    Method2: BFS
    [1,[4,[6]]] ->  1*3 + 4*2 + 6*1
    1 + (1 + 4) + (1 + 4+ 6)
     */

    public int depthSumInverse2(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        int prev = 0;
        int total = 0;
        for (NestedInteger next : nestedList) {
            queue.offer(next);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) levelSum += current.getInteger();
                else {
                    for (NestedInteger next : current.getList()) {
                        queue.offer(next);
                    }
                }
            }
            prev += levelSum;
            total += prev;
        }
        return total;
    }
}
