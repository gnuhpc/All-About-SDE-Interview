package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class TitleToNumber171 {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return s.charAt(0) - 'A'+1;

        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            num += Math.pow(26,s.length() -1 -i)* (s.charAt(i)-'A'+1);
        }

        return num;
    }

    @Test
    public void test(){
        System.out.println(titleToNumber("AAA"));
    }
}
