package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/10/17
 */
public class MostCommonWord819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(banned));

        if (paragraph.charAt(paragraph.length() - 1) != '.') paragraph = paragraph + ".";
        int max = 0, count = 0;
        String maxString = "";
        StringBuilder sb = new StringBuilder();
        for (char val : paragraph.toCharArray()) {
            if (Character.isLetter(val)) {
                sb.append(val);
            } else {
                String value = sb.toString().toLowerCase();
                sb = new StringBuilder();
                if (value.length() == 0 || set.contains(value)) continue;
                count = map.getOrDefault(value, 0) + 1;
                if (max < count) {
                    max = count;
                    maxString = value;
                }
                map.put(value, count);
            }
        }
        return maxString;
    }
}
