package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class BuildArray1441 {
    private final static String PUSH = "Push";
    private final static String POP = "Pop";

    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        // 直接逐个检查 target 与当前 num 是否匹配
        // 如果匹配呢，就直接 push 一个，然后游标 ++
        // 如果不匹配，那么就 push + pop，然后游标不动
        for (int num = 1, index = 0; num <= n && index < target.length; ++num) {
            if (num == target[index]) {
                ans.add(PUSH);
                ++index;
            } else {
                ans.add(PUSH);
                ans.add(POP);
            }
        }
        return ans;
    }
}
