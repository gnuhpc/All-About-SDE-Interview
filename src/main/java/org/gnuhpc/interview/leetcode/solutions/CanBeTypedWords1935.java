package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright gnuhpc 2021/11/12
 */

public class CanBeTypedWords1935 {
    public int canBeTypedWords(String text, String brokenLetters) {
        int count = 0;
        int j, index;
        String[] res = text.split(" ");
        for (int i = 0; i < res.length; i++) {
            for (j = 0; j < brokenLetters.length(); j++) {
                index = res[i].indexOf(brokenLetters.charAt(j));
                if (index != -1) break;
            }
            if (j == brokenLetters.length()) count++;
        }
        return count;
    }
}
