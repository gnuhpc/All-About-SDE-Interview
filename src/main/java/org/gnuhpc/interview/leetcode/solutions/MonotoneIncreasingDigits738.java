package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/12/15
 */
public class MonotoneIncreasingDigits738 {
    /**
     * 思路：
     * 从右向左扫描数字，若发现当前数字比其左边一位（较高位）小，
     * 则把其左边一位数字减1，并将该位及其右边的所有位改成9
     */
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        int length = s.length();
        char[] chars = s.toCharArray();
        int flag = length;
        for (int i = length - 1; i >= 1; i--) {
            if (chars[i] < chars[i - 1]) {
                flag = i;
                chars[i - 1]--;
            }
        }

        for (int i = flag; i < length; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(new String(chars));

    }
}
