package org.gnuhpc.bigdata.leetcode;

/*
 * 1) find all single or double chars which are valid encoding:
 * like "1", "10" etc.
 * "0", "33" or "40" are not valid.
 * record them in a graph data structure (like an array)
 * 2) use recursive (or non-recursive) way to traverse the graph by DFS, once
 * reach the last char, increment the counter by 1. if no any path exists,
 * return 0.
 * comments: graph and recursive DFS has more time and space complexity.
 * do not use int for passing by reference; use array or class with int field
 *
 */
public class NumDecodings91 {
    private int res = 0;
    private boolean[] en1;
    private boolean[] en2;
    private int length;

    public int numDecodings(String s) {
        if (s == null || s.equals("")) return 0;
        char[] cc = s.toCharArray(); //conver to char array
        length = s.length(); //number of chars
        en1 = new boolean[length];
        en2 = new boolean[length];
        for (int i = 0; i < length -1; i++) {
            if (cc[i] >= '1' && cc[i] <= '9') en1[i] = true;
            if (s.substring(i,i+2).compareTo("10") >= 0
                    && s.substring(i,i+2).compareTo("26") <= 0)
                en2[i] = true;
        }

        if (cc[length -1] >= '1' && cc[length -1] <= '9') {
            en1[length -1] = true;
        }

        dfs(0);
        return res;
    }

    public void dfs(int start) {
        if (en1[start]) {
            if (start == length -1) res++;
            else dfs(start+1);
        }
        if (en2[start]) {
            if (start == length -2) res++;
            else dfs(start+2);
        }
    }

    /*
    DP
    `dp[0]` means an empty string will have one way to decode,
    `dp[1]` means the way to decode a string of size 1.
     I then check one digit and two digit combination
     and save the results along the way.
     In the end, `dp[n]` will be the end result.
     */

    public int numDecodings2(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.parseInt(s.substring(i-1, i));
            int second = Integer.parseInt(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    //add by tina, memo search,类似于有条件的climb stairs
    public int numDecodings3(String s) {
        if(s.length()==0){
            return 0;
        }
        Integer[] dp = new Integer[s.length()];

        return dpSearch(dp, s, s.length()-1); // 注意是最后一位的索引
    }

    public int dpSearch(Integer[] dp, String s, int index){
        if(index<0){
            return 1; // 叶子节点
        }
        if(dp[index]!=null){ //Integer[]==null比int[]==0判断要快
            return dp[index];
        }
        dp[index] = 0;
        // 两种情况的加和，并且要求有条件
        if(s.charAt(index)!='0'){
            dp[index] += dpSearch(dp, s, index-1);
        }

        if(index>0 && s.charAt(index-1)!='0'){
            int num = Integer.parseInt(s.substring(index-1, index+1));
            if(num>=1 && num<=26){
                dp[index] += dpSearch(dp, s, index-2);
            }
        }

        return dp[index];
    }
}
