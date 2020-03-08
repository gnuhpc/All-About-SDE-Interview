package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxProduct318 {
    public int maxProduct(String[] words) {
        List<String> l = new ArrayList<>();
        if (words == null || words.length <= 1) return 0;
        for (String str : words) {
            char[] sArr = str.toCharArray();
            Arrays.sort(sArr);
            l.add(String.valueOf(sArr));
        }

        Collections.sort(l);

        int max = 0;

        for (int i = 0; i < l.size() - 1; i++) {
            for (int j = i + 1; j < l.size(); j++) {
                if (!isCross(l.get(i), l.get(j))) {
                    max = Math.max(max, l.get(i).length() * l.get(j).length());
                }
            }
        }

        return max;
    }

    private boolean isCross(String s1, String s2) {
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1Arr[i] == s2Arr[j]) return true;
            }
        }

        return false;
    }

    /*
    Method2: 位运算
     */
    public int maxProduct2(String[] words) {
        if (words == null || words.length <= 1) {
            return 0;
        }

        int n = words.length;
        int[] encodedWords = new int[n];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                encodedWords[i] = Utils.setBit(encodedWords[i], c - 'a'); //位运算置位
            }
        }

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((encodedWords[i] & encodedWords[j]) == 0) {
                    maxLen = Math.max(maxLen,
                                      words[i].length() * words[j].length());
                }
            }
        }

        return maxLen;
    }

    @Test
    public void test() {
        System.out.println(maxProduct2(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }
}
