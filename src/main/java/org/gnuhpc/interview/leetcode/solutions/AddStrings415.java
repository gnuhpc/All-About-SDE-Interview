package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/6/30
 */
public class AddStrings415 {
    public String addStrings(String num1, String num2) {
        StringBuilder str = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int res = num1.charAt(i) - '0' + num2.charAt(j) - '0' + carry;
            if (res >= 10) carry = 1;
            else carry = 0;
            res = res % 10;
            str.append(res);
            i--;
            j--;
        }
        while (j >= 0) {
            int res = num2.charAt(j) - '0' + carry;
            if (res >= 10) carry = 1;
            else carry = 0;
            res = res % 10;
            str.append(res);
            j--;
        }
        while (i >= 0) {
            int res = num1.charAt(i) - '0' + carry;
            if (res >= 10) carry = 1;
            else carry = 0;
            res = res % 10;
            str.append(res);
            i--;
        }
        if (carry == 1) str.append("1");
        return str.reverse().toString();
    }
}
