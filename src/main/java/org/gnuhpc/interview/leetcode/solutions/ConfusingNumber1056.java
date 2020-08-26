package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/8/18
 */
public class ConfusingNumber1056 {
    public boolean confusingNumber(int N) {
        Map<String, String> a = new HashMap<>(5);
        a.put("0", "0");
        a.put("1", "1");
        a.put("6", "9");
        a.put("8", "8");
        a.put("9", "6");
        String str = String.valueOf(N);
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!a.containsKey(String.valueOf(c))) {
                return false;
            }
            sb.append(a.get(String.valueOf(c)));
        }
        return !sb.reverse().toString().equalsIgnoreCase(str);
    }
}
