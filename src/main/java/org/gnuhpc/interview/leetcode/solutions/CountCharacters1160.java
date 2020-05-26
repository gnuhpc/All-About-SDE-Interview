package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/26
 */
public class CountCharacters1160 {
    public int countCharacters(String[] words, String chars) {
        StringBuilder sb = new StringBuilder();

        int[] dict = new int[26];

        for (char c : chars.toCharArray()) {
            dict[c - 'a']++;
        }

        for (String word : words) {
            if (isLearned(word, dict)) {
                sb.append(word);
            }
        }
        return sb.length();
    }

    private boolean isLearned(String word, int[] dict) {
        int[] map = new int[26];

        for (char c : word.toCharArray()) {
            map[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (dict[i] < map[i]) {
                return false;
            }
        }

        return true;
    }
}
