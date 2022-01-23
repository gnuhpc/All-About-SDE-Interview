package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/12/26
 */
public class FindOcurrences1078 {
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> ans = new ArrayList<String>();
        for (int i = 1; i < words.length - 1; i++) {
            if (words[i - 1].equals(first) && words[i].equals(second))
                ans.add(words[i + 1]);
        }
        return ans.toArray(new String[ans.size()]);
    }
}
