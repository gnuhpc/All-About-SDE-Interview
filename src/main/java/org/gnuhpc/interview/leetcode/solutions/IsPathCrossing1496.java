package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2021/1/12
 */
public class IsPathCrossing1496 {
    public boolean isPathCrossing(String path) {
        int[] point = new int[]{0, 0};
        Set<String> set = new HashSet<>();
        set.add("[0, 0]");
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                point[0] += 1;
            } else if (c == 'S') {
                point[0] -= 1;
            } else if (c == 'E') {
                point[1] += 1;
            } else if (c == 'W') {
                point[1] -= 1;
            }
            if (set.contains(Arrays.toString(point))) {
                return true;
            } else {
                set.add(Arrays.toString(point));
            }
        }
        return false;
    }
}
