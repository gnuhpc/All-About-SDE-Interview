package org.gnuhpc.interview.leetcode.solutions;

public class CanPermutePalindrome0104 {
    public boolean canPermutePalindrome(String s) {
        char[] chars = s.toCharArray();
        int[] target = new int[128];
        for (char c : chars) {
            target[c] += 1;
        }
        int count = 0;
        for (int i : target) {
            if(i % 2 != 0) {
                count++;
            }
            if(count > 1) {
                return false;
            }
        }
        return true;
    }

}
