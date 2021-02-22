package org.gnuhpc.interview.leetcode.solutions;

public class ConvertInteger0506 {
    public int convertInteger(int A, int B) {
        return Integer.bitCount(A^B);
    }
}
