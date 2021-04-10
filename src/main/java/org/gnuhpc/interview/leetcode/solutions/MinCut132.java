package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

//https://leetcode.com/problems/Palindrome-Partitioning-II/discuss/175370/Java-DFS-Recursion-with-cache
public class MinCut132 {
    public int minCut(String s) {
        int[] mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return p(s, 0, mem) - 1;
    }

    //递归含义：分割[begin...]需要的最小次数
    //子问题：aaabaa a是回文串，则aaabaa的最小分割次数是(1+0)-1=0
    private int p(String s, int begin, int[] mem) {
        if (begin == s.length()) {
            return 0;
        }
        if (mem[begin] != -1) {
            return mem[begin];
        }
        int min = Integer.MAX_VALUE;
        for (int i = begin; i < s.length(); i++) {
            if (isPalindrome(s, begin, i)) {
                min = Math.min(min, 1 + p(s, i + 1, mem));
            }
        }
        mem[begin] = min;
        return mem[begin];
    }

    private boolean isPalindrome(String s, int begin, int end) {
        while (begin < end) {
            if (s.charAt(begin++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
