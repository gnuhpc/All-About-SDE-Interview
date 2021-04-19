package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2021/3/2
 */
public class CountMatches1773 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        Map<String, Integer> map = new HashMap<>();
        map.put("type", 0);
        map.put("color", 1);
        map.put("name", 2);

        int ruleCol = map.get(ruleKey);

        int res = 0;
        for (List<String> item : items) {
            if (item.get(ruleCol).equals(ruleValue)) {
                res++;
            }
        }

        return res;
    }
}
