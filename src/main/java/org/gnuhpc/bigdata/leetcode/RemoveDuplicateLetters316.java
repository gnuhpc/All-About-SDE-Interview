package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 19-8-12
 */
public class RemoveDuplicateLetters316 {
    private int                length;
    private HashSet<Character> charSet;
    private HashSet<Character> resultSet;

    public String removeDuplicateLetters(String s) {
        charSet = new HashSet<>();
        resultSet = new HashSet<>();

        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        length = charSet.size();

        return doRemove(s);
    }

    private String doRemove(String s) {
        if (length == 0) return "";
        if (s == null) return null;
        if (s.length() == 1) return s;

        int[] map = new int[26];
        char[] strArr = s.toCharArray();

        for (char c : strArr) {
            map[c - 'a']++;
        }

        char c = 'a';

        for (; c <= 'z'; c++) {
            if (map[c - 'a'] > 0 && charSet.contains(c)) {
                map[c - 'a'] = -map[c - 'a'];
                break;
            }
        }

        resultSet.stream().forEach(e -> {
            map[e - 'a'] = -map[e - 'a'];
        });

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
                length--;
                charSet.remove(c);
                resultSet.add(c);
                return c + doRemove(substr);
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

        return c + "";
    }


    @Test
    public void test() {
//        System.out.println(removeDuplicateLetters("cbacdcbc"));
//        System.out.println(removeDuplicateLetters("baab"));
        System.out.println(removeDuplicateLetters("bbcaac"));
    }
}
