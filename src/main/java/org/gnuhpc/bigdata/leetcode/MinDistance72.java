package org.gnuhpc.bigdata.leetcode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.min;

//https://www.javacodegeeks.com/2014/03/easy-to-understand-dynamic-programming-edit-distance.html
public class MinDistance72 {
    /*
    Method 1: Recursive LTE
     */
    public int minDistance1(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        if (word1.equals(word2)) return 0;
        int replace = minDistance1(word1.substring(1), word2.substring(1)) + (word1.charAt(0) == word2.charAt(0) ? 0 : 1);
        int delete = minDistance1(word1.substring(1), word2) + 1;
        int insert = minDistance1(word1, word2.substring(1)) + 1;
        return min(replace, delete, insert);
    }

    /*
    Method 2: Recursive Memorization almost pass LTE
     */
    public int minDistance2(String word1, String word2) {
        Map<String, Integer> memo = new HashMap<>();
        return getdis(word1,word2,memo);
    }

    private int getdis(String word1, String word2, Map<String, Integer> memo) {
        String key = word1+"_"+word2;
        if (word1.isEmpty()) {
            memo.put(key,word2.length());
            return word2.length();
        }
        if (word2.isEmpty()) {
            memo.put(key,word1.length());
            return word1.length();
        }
        if (word1.equals(word2)) {
            memo.put(key,0);
            return 0;
        }
        if (memo.containsKey(key)) return memo.get(key);

        int replace = getdis(word1.substring(1), word2.substring(1),memo) + (word1.charAt(0) == word2.charAt(0) ? 0 : 1);
        int delete = getdis(word1.substring(1), word2, memo) + 1;
        int insert = getdis(word1, word2.substring(1),memo) + 1;
        int res = min(replace, delete, insert);
        memo.put(key,res);
        return res;
    }


    /*
    Method 3 : DP
    解决两个字符串的动态规划问题，一般都是用两个指针 i,j 分别指向两个字符串的最后，然后一步步往前走，缩小问题的规模。
    This is a classic problem of Dynamic Programming.
    We define the state `dp[i][j]` to be the minimum number of operations
    to convert `word1[0..i - 1]` to `word2[0..j - 1]`.
    The state equations have two cases: the boundary case and the general case.
    Note that in the above notations, both `i` and `j` take values starting from `1`.

For the boundary case, that is, to convert a string to an empty string, it is easy to see that the mininum number of operations to convert `word1[0..i - 1]` to `""` requires at least `i` operations (deletions). In fact, the boundary case is simply:

 1. `dp[i][0] = i`;
 2. `dp[0][j] = j`.

Now let's move on to the general case, that is, convert a non-empty `word1[0..i - 1]` to another non-empty `word2[0..j - 1]`.
Well, let's try to break this problem down into smaller problems (sub-problems). Suppose we have already known how to convert `word1[0..i - 2]` to `word2[0..j - 2]`, which is `dp[i - 1][j - 1]`. Now let's consider `word[i - 1]` and `word2[j - 1]`. If they are euqal, then no more operation is needed and `dp[i][j] = dp[i - 1][j - 1]`. Well, what if they are not equal?

If they are not equal, we need to consider three cases:

 1. Replace `word1[i - 1]` by `word2[j - 1]` (`dp[i][j] = dp[i - 1][j - 1] + 1 (for replacement)`);
 2. Delete `word1[i - 1]` and `word1[0..i - 2] = word2[0..j - 1]` (`dp[i][j] = dp[i - 1][j] + 1 (for deletion)`);
 3. Insert `word2[j - 1]` to `word1[0..i - 1]` and `word1[0..i - 1] + word2[j - 1] = word2[0..j - 1]` (`dp[i][j] = dp[i][j - 1] + 1 (for insertion)`).

Make sure you understand the subtle differences between the equations for deletion and insertion. For deletion, we are actually converting `word1[0..i - 2]` to `word2[0..j - 1]`, which costs `dp[i - 1][j]`,  and then deleting the `word1[i - 1]`, which costs `1`. The case is similar for insertion.

Putting these together, we now have:

 1. `dp[i][0] = i`;
 2. `dp[0][j] = j`;
 3. `dp[i][j] = dp[i - 1][j - 1]`, if `word1[i - 1] = word2[j - 1]`;
 4. `dp[i][j] = min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1, dp[i][j - 1] + 1)`, otherwise.
     */
    public int minDistance3(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(m==0) return n;
        if(n==0) return m;
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++) dp[i][0] = i;
        for(int j=0;j<=n;j++) dp[0][j] = j;
        for(int i=1;i<m+1;i++)
            for(int j=1;j<n+1;j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = min(dp[i-1][j-1], dp[i-1][j],dp[i][j-1])+1;
            }
        return dp[m][n];
    }

}
