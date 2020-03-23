package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/3/23
 */
public class NumUniqueEmails929 {
    public int numUniqueEmails(String[] emails) {
        Set<String> uniEmailSet = new HashSet<>();

        for (String email : emails) {
            uniEmailSet.add(parse(email));
        }

        return uniEmailSet.size();
    }

    private String parse(String email) {
        StringBuilder sb = new StringBuilder();
        boolean ignore = false;
        boolean domain = false;

        char[] chars = email.toCharArray();

        for (char c : chars) {
            if (c == '.' && !domain) continue;
            if (c == '+') ignore = true;
            if (c == '@') {
                domain = true;
                ignore = false;
            }
            if (!ignore) sb.append(c);
        }

        return sb.toString();
    }
}
