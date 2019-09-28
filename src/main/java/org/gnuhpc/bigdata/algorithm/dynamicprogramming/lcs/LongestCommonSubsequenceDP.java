package org.gnuhpc.bigdata.algorithm.dynamicprogramming.lcs;

//https://www.youtube.com/watch?v=cfCdtJSu5pc
public class LongestCommonSubsequenceDP {
    public static void main(String args[]) {
        long timestamp1 = System.currentTimeMillis();
        System.out.println("---------------Longest Common Subsequence Using Recursive Method And Memoization-----------------");
        String string1 = "nematode knowledge";
        String string2 = "empty bottle";
        String longestCommonSubsequence = lcs(string1.toCharArray(), string2.toCharArray());
        System.out.println("LCS is " + longestCommonSubsequence);
        long timestamp2 = System.currentTimeMillis();
        System.out.println(" Time Taken : " + (timestamp2 - timestamp1));
    }

    public static String lcs(char[] a, char[] b) {
        int[][] lengths = new int[a.length+1][b.length+1];

        // 先算长度
        // row 0 and column 0 are initialized to 0 already
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b.length; j++)
                if (a[i] == b[j])
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);
        System.out.println("First get the length of LCS: " +
                lengths[a.length][b.length]);


        // read the substring out from the matrix
        StringBuffer sb = new StringBuffer();
        for (int x = a.length, y = b.length;
             x > 0 && y > 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert a[x-1] == b[y-1];
                sb.append(a[x-1]);
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }
}
