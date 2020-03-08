package org.gnuhpc.bigdata.leetcode.solutions;

public class FindTheDifference398 {
    public char findTheDifference(String s, String t) {
        int res = -1;
        if (s == null || t == null) return (char) (res + 'a');

        int[] map = new int[26];

        for (char c : t.toCharArray()) {
            map[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            map[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (map[i] == 1) {
                res = 'a' + i;
            }
        }

        return (char) res;
    }
}
