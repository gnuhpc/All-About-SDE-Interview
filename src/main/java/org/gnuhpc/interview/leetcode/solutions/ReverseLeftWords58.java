package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/9
 */
public class ReverseLeftWords58 {
    public String reverseLeftWords(String s, int n) {
        char[] seq = s.toCharArray();
        reverse(seq, 0, n - 1);
        reverse(seq, n, seq.length - 1);
        reverse(seq, 0, seq.length - 1);
        return new String(seq);
    }

    private void reverse(char[] seq, int s, int e) {
        while (s < e) {
            char tmp = seq[s];
            seq[s] = seq[e];
            seq[e] = tmp;
            s++;
            e--;
        }
    }

    public String reverseLeftWords2(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}
