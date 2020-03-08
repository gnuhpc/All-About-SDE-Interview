package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

public class ConvertToTitle168 {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();

        while (n > 0) {
            int k = n % 26;
            if (k == 0) {
                n -= 26;
                k = 26;
            }

            char c = (char) ((k - 1) + (int) 'A');
            sb.insert(0, c);
            n = n / 26;
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(convertToTitle(28));
    }

    //add by tina
    // The key is n--. The minimum in 26-bit number is mapped to 1, not 0.
    public String convertToTitle2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is not valid!");
        }

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            n--;
            char ch = (char) (n % 26 + 'A');
            n /= 26;
            sb.append(ch); //sb.insert(0,ch);
        }

        sb.reverse();
        return sb.toString();
    }
}
