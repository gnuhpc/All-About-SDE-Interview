package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 19-8-12
 */
public class CountAndSay38 {
    class Tuple {
        private final int num;
        private final int count;

        public Tuple(int count, int num) {
            this.count = count;
            this.num = num;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(count);
            sb.append(num);

            return sb.toString();
        }
    }

    public String countAndSay(int n) {
        String str = "1";
        StringBuilder sb = new StringBuilder();

        if (n == 1) return str;
        for (int i = 1; i < n; i++) {
            int count = 1;
            char num = str.charAt(0);
            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j) == num) count++;
                else {
                    sb.append(count);
                    sb.append(num - '0');
                    count = 1;
                    num = str.charAt(j);
                }
            }

            sb.append(count);
            sb.append(num - '0');

            str = sb.toString();
            sb.delete(0, sb.length());
        }

        return str;
    }


    @Test
    public void test() {
        System.out.println(countAndSay(5));
    }
}
