package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2021/4/3
 */
public class GetValidT9Words1620 {
    public List<String> getValidT9Words(String num, String[] words) {
        Map<Integer, char[]> map = new HashMap<Integer, char[]>() {{
            put(2, new char[]{'a', 'b', 'c'});
            put(3, new char[]{'d', 'e', 'f'});
            put(4, new char[]{'g', 'h', 'i'});
            put(5, new char[]{'j', 'k', 'l'});
            put(6, new char[]{'m', 'n', 'o'});
            put(7, new char[]{'p', 'q', 'r', 's'});
            put(8, new char[]{'t', 'u', 'v'});
            put(9, new char[]{'w', 'x', 'y', 'z'});
        }};
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (num.length() != word.length())
                continue;
            char[] chars = word.toCharArray();
            int i = 0;
            for (; i < chars.length; i++) {
                char[] candidate = map.get(Character.getNumericValue(num.charAt(i)));
                int j = 0;
                for (; j < candidate.length; j++) {
                    if (candidate[j] == chars[i])
                        break;
                }
                if (j == candidate.length)
                    break;
            }
            if (i == chars.length)
                result.add(word);
        }
        return result;
    }
}
