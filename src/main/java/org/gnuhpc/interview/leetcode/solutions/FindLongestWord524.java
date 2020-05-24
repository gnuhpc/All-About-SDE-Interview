package org.gnuhpc.interview.leetcode.solutions;

import java.util.List;

/**
 * Copyright gnuhpc 2020/5/24
 */
public class FindLongestWord524 {
    public String findLongestWord(String s, List<String> d) {
        int max = Integer.MIN_VALUE;
        String res = "";

        for (String word : d) {
            if (isMatch(s, word)) {
                //System.out.println(word);
                int nLen = word.length();
                if (nLen > max) {
                    res = word;
                    max = nLen;
                } else if (nLen == max) {
                    // System.out.println(word.compareTo(res));
                    if (word.compareTo(res) < 0) {
                        res = word;
                    }
                }
            }
        }

        return res;
    }

    private boolean isMatch(String s, String word) {

        int i = 0, j = 0;
        char[] sa = s.toCharArray();
        char[] wa = word.toCharArray();
        int sLen = s.length();
        int wLen = word.length();

        while (i < sLen && j < wLen) {
            if (sa[i] == wa[j]) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        return !(i == sLen && j < wLen);
    }
}
