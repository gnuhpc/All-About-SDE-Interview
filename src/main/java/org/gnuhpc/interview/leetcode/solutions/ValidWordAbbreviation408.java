package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/1/10
 */
public class ValidWordAbbreviation408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr == null || abbr.length() == 0) return false;

        if (word.isEmpty()) {
            return abbr.isEmpty();
        }

        int p1 = 0;
        int p2 = 0;

        while (p1 < word.length() && p2 < abbr.length()) {
            if (!Character.isDigit(abbr.charAt(p2))) {
                if (word.charAt(p1) == abbr.charAt(p2)) {
                    p1++;
                    p2++;
                } else {
                    return false;
                }
            } else {
                // edge case: leading zero
                //
                if (abbr.charAt(p2) == '0') {
                    return false;
                }

                int num = 0;
                while (p2 < abbr.length() && Character.isDigit(abbr.charAt(p2))) {
                    int digit = Character.getNumericValue(abbr.charAt(p2));
                    num = num * 10 + digit;
                    p2++;
                }

                p1 += num;
            }
        }

        return p1 == word.length() && p2 == abbr.length();
    }

    @Test
    public void test() {
        System.out.println(validWordAbbreviation("internationalization",
                "i12iz4n"));
    }
}
