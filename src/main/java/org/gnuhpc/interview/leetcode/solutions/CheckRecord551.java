package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/4
 */
public class CheckRecord551 {
    public boolean checkRecord(String s) {
        int numA = 0;
        int numL = 0;
        boolean isLate = false;

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == 'A') {
                numA++;
                numL = 0;
            } else if (c == 'L') {
                numL++;
            } else {
                numL = 0;
            }

            if (numL == 3) isLate = true;
        }

        if (isLate) return false;


        return numA <= 1 && numL <= 2;
    }
}
