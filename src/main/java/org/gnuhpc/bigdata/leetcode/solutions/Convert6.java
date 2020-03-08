package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-30 6:51
 * Version: 0.1
 */
public class Convert6 {
    /*
    从右往左看过去，将空格压缩掉就是这个结构
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) sb[i] = new StringBuilder();
        int idx = 0, k = 1;
        for (char c : s.toCharArray()) {
            sb[idx].append(c);
            if (idx == 0) k = 1;
            if (idx == numRows - 1) k = -1;
            idx += k;
        }
        return String.join("", sb);
    }

    @Test
    public void test() {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public String convert2(String s, int numRows) {
        if (s == null || numRows <= 0) return "";
        if (numRows == 1) return s;
        int i = 0;
        int j = 0;
        int k = 0;
        int n = s.length();
        char[][] ch = new char[numRows][n / 2 + 1];
        while (k < n) {
            while (i < numRows && k < n) {
                ch[i++][j] = s.charAt(k++);
            }//i=numRows
            i = i - 2;
            j = j + 1;
            while (i > 0 && k < n) {
                ch[i--][j++] = s.charAt(k++);
            }//i=0,j=numsRows*num

        }

        StringBuilder sb = new StringBuilder();
        for (int ii = 0; ii < numRows; ii++) {
            for (int jj = 0; jj < ch[0].length; jj++) {
                //System.out.print(ch[ii][jj]+",");
                if (ch[ii][jj] != '\u0000') sb.append(ch[ii][jj]);
            }
        }
        return sb.toString();
    }
}
