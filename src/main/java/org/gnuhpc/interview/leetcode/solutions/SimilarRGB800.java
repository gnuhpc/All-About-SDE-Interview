package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/12/24
 */
public class SimilarRGB800 {
    public String similarRGB(String color) {
        int n = color.length();
        int max = Integer.MIN_VALUE;
        String x = color.substring(1, 3);
        String y = color.substring(3, 5);
        String z = color.substring(5, 7);
        String t1 = "";
        String t2 = "";
        String t3 = "";
        int n1 = hex(x);
        int n2 = hex(y);
        int n3 = hex(z);
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        for (int i = 0; i <= 15; i++) {
            for (int j = 0; j <= 15; j++) {
                for (int k = 0; k <= 15; k++) {
                    //分析16进制的 0x00、0x11、0x22、...、0xff 的规律，发现每一个元素都是上一个加了一个 0x11，也就是十进制的 17。也就是说，可以简写的颜色数值其实都是十进制数 17 的倍数。
                    int temp = -(n1 - 17 * i) * (n1 - 17 * i) - (n2 - 17 * j) * (n2 - 17 * j) - (n3 - 17 * k) * (n3 - 17 * k);
                    if (temp > max) {
                        max = temp;
                        r1 = i;
                        r2 = j;
                        r3 = k;
                    }
                }
            }
        }
        char[] list = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        t1 = "" + list[r1] + list[r1];
        t2 = "" + list[r2] + list[r2];
        t3 = "" + list[r3] + list[r3];
        return "#" + t1 + t2 + t3;
    }

    public int hex(String s) {
        return Integer.parseInt(s, 16);
    }
}
