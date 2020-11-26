package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/22
 */
public class ComplexNumberMultiply537 {
    public String complexNumberMultiply(String a, String b) {
        // (a+bi)(c+di) = ac + adi + bci + bdi^2
        String[] split1 = a.split("\\+");
        String[] split2 = b.split("\\+");
        int A = Integer.parseInt(split1[0]);
        int B = Integer.parseInt(split1[1].split("i")[0]);
        int C = Integer.parseInt(split2[0]);
        int D = Integer.parseInt(split2[1].split("i")[0]);
        return (A * C - B * D) + "+" + (A * D + B * C) + "i";
    }
}
