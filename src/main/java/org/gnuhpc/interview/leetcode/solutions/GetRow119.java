package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/6/25
 */
public class GetRow119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();

        if (rowIndex == 0) {
            ans.add(1);
            return ans;
        }

        if (rowIndex == 1) {
            ans.add(1);
            ans.add(1);
            return ans;
        }

        int x;
        List<Integer> last = getRow(rowIndex - 1);
        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0 || i == rowIndex) {
                x = 1;
            } else {
                x = last.get(i - 1) + last.get(i);
            }

            ans.add(x);
        }
        return ans;

    }
}
