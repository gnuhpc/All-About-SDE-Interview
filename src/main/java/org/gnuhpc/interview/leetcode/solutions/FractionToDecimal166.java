package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/6/30
 */
public class FractionToDecimal166 {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuffer sb = new StringBuffer();
        long a = numerator;
        long b = denominator;
        if (a * b < 0)
            sb.append('-');
        if (a < 0)
            a = -a;
        if (b < 0)
            b = -b;
        sb.append(a / b);
        a = 10 * (a % b);
        if (a != 0)
            sb.append('.');
        int index = sb.length();
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (; a != 0; a = 10 * (a % b)) {
            if (map.containsKey(a)) {
                sb.insert(map.get(a), "(");
                sb.append(')');
                break;
            }
            sb.append(a / b);
            map.put(a, index++);
        }
        return sb.toString();
    }
}
