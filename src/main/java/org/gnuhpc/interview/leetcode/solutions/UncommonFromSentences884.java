package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2021/4/10
 */
public class UncommonFromSentences884 {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap();
        String[] str1 = A.split(" ");
        String[] str2 = B.split(" ");

        for (String s : str1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (String s : str2) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        Set<String> set = map.keySet();
        List<String> list = new ArrayList();

        for (String key : set) {
            if (map.get(key) == 1) {
                list.add(key);
            }

        }
        return list.toArray(new String[0]);
    }
}
