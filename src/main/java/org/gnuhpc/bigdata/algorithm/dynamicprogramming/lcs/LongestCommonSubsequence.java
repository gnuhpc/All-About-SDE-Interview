package org.gnuhpc.bigdata.algorithm.dynamicprogramming.lcs;


public class LongestCommonSubsequence {
    public static void main(String args[]) {
        System.out.println("---------------Longest Common Subsequence Using Recursive Method-----------------");
        String string1 = "AGGTABNDSV";
        String string2 = "GXTXAYBDFGHOI";
        int lcsLength = lcsLength(string1,string2);
        System.out.println("The length of LCS is " + lcsLength);
        int lcsLengthIdx = lcsLength(string1,string2);
        System.out.println("The length of LCS (using idx method) is " + lcsLengthIdx);
        System.out.println();

        long timestamp1 = System.currentTimeMillis();
        String longestCommonSubsequence = lcs(string1, string2);
        System.out.println("LCS is " + longestCommonSubsequence);
        long timestamp2 = System.currentTimeMillis();
        System.out.println(" Time Taken : " + (timestamp2 - timestamp1));

        timestamp1 = System.currentTimeMillis();
        System.out.println("LCS(using idx) is " + lcsIdx(string1,string2));
        timestamp2 = System.currentTimeMillis();
        System.out.println(" Time Taken : " + (timestamp2 - timestamp1));
    }

    private static String lcs(String string1, String string2) {
        int aLen = string1.length();
        int bLen = string2.length();
        if(aLen == 0 || bLen == 0){
            return "";
        }else if(string1.charAt(0) == string2.charAt(0)){
            return string1.charAt(0) + lcs(string1.substring(1,aLen),string2.substring(1,bLen));
        }else{
            String x = lcs(string1, string2.substring(1,bLen));
            String y = lcs(string1.substring(1,aLen), string2);
            return (x.length() > y.length()) ? x : y;
        }
    }

    private static String lcsIdx(String string1,String string2){
        return solveLCS(string1.toCharArray(),string2.toCharArray(),0,0);
    }

    private static String solveLCS(char[] string1, char[] string2, int i, int j) {
        if (string1.length == i || string2.length == j) return "";
        else if (string1[i] == string2[j]) return string1[i]  + solveLCS(string1, string2,i+1, j+1);
        else {
            String x = solveLCS(string1, string2,i+1,j);
            String y = solveLCS(string1, string2, i ,j+1);
            return (x.length() > y.length()) ? x : y;
        }
    }

    private static int lcsLength(String string1, String string2){
        int aLen = string1.length();
        int bLen = string2.length();
        if(aLen == 0 || bLen == 0) {
            return 0;
        } else if (string1.charAt(0) == string2.charAt(0)){
            return 1 + lcsLength(string1.substring(1,aLen),string2.substring(1,bLen));
        } else {
            return Math.max(lcsLength(string1,string2.substring(1,bLen)),
                    lcsLength(string1.substring(1,aLen),string2)
            );
        }
    }

    private static int lcsLengthWithIdx(String string1,String string2){
        return solve(string1,string2,0,0);
    }

    private static int solve(String string1, String string2, int i, int j) {
        if (string1.length() == i || string2.length() == j) return 0;
        else if (string1.charAt(i) == string2.charAt(j)) return 1 + solve(string1, string2,i+1, j+1);
        else return Math.max(solve(string1,string2,i+1, j), solve(string1,string2,i, j+1));
    }
}
