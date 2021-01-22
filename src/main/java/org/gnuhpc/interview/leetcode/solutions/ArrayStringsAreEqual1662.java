package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/14
 */
public class ArrayStringsAreEqual1662 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder w1 = new StringBuilder();
        StringBuilder w2 = new StringBuilder();
        for (String s : word1) {
            w1.append(s);
        }
        for (String s : word2) {
            w2.append(s);
        }
        return w1.toString().equals(w2.toString());
    }
}
