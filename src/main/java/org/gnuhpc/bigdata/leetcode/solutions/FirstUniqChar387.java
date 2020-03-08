package org.gnuhpc.bigdata.leetcode.solutions;

/**
 * Copyright gnuhpc 19-8-9
 */
public class FirstUniqChar387 {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        if (s.length() == 1) return 0;

        int[] map = new int[26];

        char[] sArr = s.toCharArray();

        for (int i = 0; i < sArr.length; i++) {
            map[sArr[i] - 'a']++;
        }

        for (int i = 0; i < sArr.length; i++) {
            if (map[sArr[i] - 'a'] == 1) return i;
        }

        return -1;

    }
}
