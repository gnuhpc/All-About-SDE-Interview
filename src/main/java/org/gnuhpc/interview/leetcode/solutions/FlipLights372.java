package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/25
 */
public class FlipLights372 {
    public int flipLights(int n, int m) {
        if (n == 0 || m == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        } else if (n == 2 && m == 1) {
            return 3;
        } else if ((n == 2 && m >= 2) || m == 1) {
            // n < 3的情况已被前面的if排除
            // 因此当n>=3时，只需判断m==1即可
            return 4;
        } else if (n >= 3 && m == 2) {
            return 7;
        } else {
            return 8;
        }
    }
}
