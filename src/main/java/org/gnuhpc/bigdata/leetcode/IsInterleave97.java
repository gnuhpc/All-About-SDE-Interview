package org.gnuhpc.bigdata.leetcode;

public class IsInterleave97 {
    /* DFS + Memo + 多指针
    To solve this problem, let's look at if `s1[0 ~ i]`  `s2[0 ~ j]` can be interleaved to `s3[0 ~ k]`.
- Start from indices`0, 0, 0` and compare `s1[i] == s3[k]` or `s2[j] == s3[k]`
- Return valid only if either `i` or `j` match `k` and the remaining is also valid
- Caching is the key to performance. This is very similar to top down dp
- Only need to cache `invalid[i][j]` since most of the case `s1[0 ~ i]` and `s2[0 ~ j]` does not form `s3[0 ~ k]`. Also tested caching `valid[i][j]` the run time is also `1ms`
- Many guys use `substring` but it's duplicate code since `substring` itself is checking char by char. We are already doing so
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) return false;
        return dfs(c1, c2, c3, 0, 0, 0, new boolean[m + 1][n + 1]);
    }

    public boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
        if(invalid[i][j]) return false;
        if(k == c3.length) return true;
        boolean valid =
                i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j, k + 1, invalid) ||
                        j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid);
        if(!valid) invalid[i][j] = true;
        return valid;
    }


}
