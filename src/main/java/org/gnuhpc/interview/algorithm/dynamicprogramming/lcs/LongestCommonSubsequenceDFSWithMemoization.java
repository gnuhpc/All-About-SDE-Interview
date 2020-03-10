package org.gnuhpc.interview.algorithm.dynamicprogramming.lcs;

import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=Qf5R-uYQRPk
public class LongestCommonSubsequenceDFSWithMemoization {
    private static Map<String, String> subStringLcsMap = new HashMap<>();

    public static void main(String args[]) {
        long timestamp1 = System.currentTimeMillis();
        System.out.println("---------------Longest Common Subsequence Using Recursive Method And Memoization-----------------");
        String string1 = "AGGTABNDSV";
        String string2 = "GXTXAYBDFGHOI";
        String longestCommonSubsequence = lcs(string1, string2);
        System.out.println("LCS is " + longestCommonSubsequence);
        long timestamp2 = System.currentTimeMillis();
        System.out.println(" Time Taken : " + (timestamp2 - timestamp1));
    }

    private static String lcs(String a, String b) {
        String res;
        String key = a + "-" + b;
        String value = subStringLcsMap.get(key);
        if (value != null && !value.isEmpty()) return value;
        int aLen = a.length();
        int bLen = b.length();
        if (aLen == 0 || bLen == 0) {
            res = "";
        } else if (a.charAt(0) == b.charAt(0)) {
            res = a.charAt(0) + lcs(a.substring(1, aLen), b.substring(1, bLen));
        } else {
            String x = lcs(a, b.substring(1, bLen));
            String y = lcs(a.substring(1, aLen), b);
            res = (x.length() > y.length()) ? x : y;
        }
        subStringLcsMap.put(key, res);
        return res;
    }
}
