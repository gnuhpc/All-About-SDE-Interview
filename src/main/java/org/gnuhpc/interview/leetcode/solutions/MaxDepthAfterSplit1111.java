package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class MaxDepthAfterSplit1111 {

    public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];
        for (int i = 0; i < seq.length(); i = i + 2) {
            char ch1 = seq.charAt(i);
            char ch2 = seq.charAt(i + 1);
            if (ch1 == ch2) {
                res[i] = 0;
                res[i + 1] = 1;
            } else {
                res[i] = 0;
                res[i + 1] = 0;
            }
        }
        return res;
    }

    @Test
    public void test() {
        Utils.printArray(maxDepthAfterSplit("((()))"));
    }
}
