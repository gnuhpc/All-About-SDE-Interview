package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-12
 */
public class RemoveDuplicateLetters316 {
    public String removeDuplicateLetters(String s) {
        if (s == null) return null;
        if (s.length() == 1) return s;

        int[] map = new int[26];
        char[] strArr = s.toCharArray();

        for (char c : strArr) {
            map[c - 'a']++;
        }

        char c = 'a';

        for (; c <= 'z'; c++) {
            if (map[c - 'a'] > 0) {
                map[c - 'a'] = -map[c - 'a'];
                break;
            }
        }

        int idx = s.indexOf(c);
        String substr = s.substring(idx + 1);

        while (!substr.isEmpty()) {
            for (char cc : substr.toCharArray()) {
                if (map[cc - 'a'] > 0) {
                    map[cc - 'a'] = -map[cc - 'a'];
                }
            }

            boolean valid = true;

            for (int i = 0; i < 26; i++) {
                if (map[i] > 0) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return c + removeDuplicateLetters(substr);
            }
            else {
                for (char cc : substr.toCharArray()) {
                    if (map[cc - 'a'] < 0) {
                        map[cc - 'a'] = -map[cc - 'a'];
                    }
                }

                c += 1;
                for (; c <= 'z'; c++) {
                    if (map[c - 'a'] > 0) {
                        map[c - 'a'] = -map[c - 'a'];
                        break;
                    }
                }

                idx = s.indexOf(c);
                substr = s.substring(idx + 1);
            }
        }

        return "";
    }

    @Test
    public void test() {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }
}
