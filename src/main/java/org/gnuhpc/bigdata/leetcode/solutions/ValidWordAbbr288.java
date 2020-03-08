package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Copyright gnuhpc 2019/9/30
 */
public class ValidWordAbbr288 {

    Map<String, String> map;

    public ValidWordAbbr288(String[] dictionary) {
        map = new HashMap<>();
        for (String str : dictionary) {
            String key = getKey(str);
            // If there is more than one string belong to the same key
            // then the key will be invalid, we set the value to ""
            if (map.containsKey(key)) {
                if (!map.get(key).equals(str)) {
                    map.put(key, "");
                }
            }
            else {
                map.put(key, str);
            }
        }
    }

    public boolean isUnique(String word) {
        String key = getKey(word);
        return !map.containsKey(key) || map.get(key).equals(word);
    }

    private String getKey(String str) {
        if (str.length() <= 2) return str;
        return str.charAt(0) + Integer.toString(str.length() - 2) + str.charAt(str.length() - 1);
    }
}
