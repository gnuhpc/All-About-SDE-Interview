package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 19-8-9
 */
public class CanConstruct383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null) return false;
        if (ransomNote.length() == 0 && magazine.length() == 0) return true;
        if (ransomNote.length() > magazine.length()) return false;

        int[] map1 = count(ransomNote);
        int[] map2 = count(magazine);

        for (int i = 0; i < map1.length; i++) {
            if (map2[i] - map1[i] < 0) return false;
        }

        return true;
    }

    private int[] count(String str) {
        int[] map = new int[26];

        for (char c : str.toCharArray()) {
            map[c - 'a']++;
        }

        return map;
    }
}
