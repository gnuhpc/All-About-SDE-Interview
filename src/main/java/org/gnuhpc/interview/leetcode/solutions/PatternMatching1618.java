package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/6/23
 */
public class PatternMatching1618 {
    Map<Character, String> map = new HashMap<>();

    public boolean patternMatching(String pattern, String value) {
        if (pattern.length() == 0 && value.length() == 0) return true;
        else if (pattern.length() == 0) return false;
        char c = pattern.charAt(0);
        if (map.containsKey(c)) {
            String s = map.get(c);
            if (s.length() > value.length()) return false;
            if (value.startsWith(s))
                return patternMatching(pattern.substring(1), value.substring(s.length()));
            else return false;
        } else {
            for (int i = 0; i <= value.length(); i++) {
                String s = value.substring(0, i);
                if (map.containsValue(s)) continue;
                map.put(c, s);
                if (patternMatching(pattern.substring(1), value.substring(i))) return true;
            }
            map.remove(c);
            return false;
        }
    }
}
