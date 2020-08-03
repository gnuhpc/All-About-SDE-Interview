package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/2
 */
public class ConvertToBase7504 {
    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        String sign = "";
        if (num == 0) return "0";
        if (num < 0) {
            num = 0 - num;
            sign = "-";
        }

        while (num > 0) {
            int mod = num % 7;
            sb.append((char) ('0' + mod));
            num = num / 7;
        }

        return sign + sb.reverse().toString();

    }
}
