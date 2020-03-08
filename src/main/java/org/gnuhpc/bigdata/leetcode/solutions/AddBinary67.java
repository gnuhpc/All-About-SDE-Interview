package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-20
 */
public class AddBinary67 {
    public String addBinary(String a, String b) {
        String l = a.length() > b.length() ? a : b;
        String s = a.length() > b.length() ? b : a;
        StringBuilder sb = new StringBuilder();

        int diff = l.length() - s.length();

        int left = 0, i = s.length() - 1;
        for (; i >= 0; i--) {
            int num1 = s.charAt(i) - '0';
            int num2 = l.charAt(i + diff) - '0';

            int num = num1 + num2 + left;

            if (num < 2) {
                left = 0;
            }
            else if (num == 2) {
                left = 1;
                num = 0;
            }
            else { //num == 3
                left = 1;
                num = 1;
            }

            sb.append(num);
        }

        for (i = diff - 1; i >= 0; i--) {
            int num = l.charAt(i) - '0' + left;


            if (num < 2) {
                left = 0;
            }
            else if (num == 2) {
                left = 1;
                num = 0;
            }
            else { //num == 3
                left = 1;
                num = 1;
            }


            sb.append(num);
        }

        if (left == 1) sb.append(left);

        return sb.reverse().toString();
    }

    @Test
    public void test() {
//        System.out.println(addBinary("11","1"));
//        System.out.println(addBinary("1010","1011"));
//        System.out.println(addBinary("1111","1111"));
        System.out.println(addBinary("100", "110010"));
    }
}
