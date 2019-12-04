package org.gnuhpc.bigdata.leetcode;

public class NumberOfPatterns351 {
    //非常标准的回溯， 在valid中判断所有场景
    /*

Intuitively, we could use DFS backtracking to solve this problem.

The difficulty is that at a current state, how do we check which number we can go next?

We create a skip array to handle this.


In other words, if we skip this number, the output will be invalid.

Now we deal with the DFS.

We find 1, 3, 7, 9 are symmetric. 2, 4, 6, 8 are also symmetric.

This means that if we know the total number of valid patterns start from 1, we know the numbers start from 3, 7, and 9.

If we know the total number of valid patterns start from 2, we know the numbers start from 4, 6, and 8.
     */

    //skip[i][j] denotes if we go from i to j, which number we cannot skip.
    //skip[i][j] == 0 代表没有数字不能skip
    public final int[][] skip = new int[10][10];

    public int numberOfPatterns(int m, int n) {
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
        skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = 5;

        int result = 0;
        //visited 为本次是否被访问的标记
        boolean[] visited = new boolean[10];
        //visited[0]则表示没有需要跳过的数字，恒定为true，见visited[skip[curr][next]].
        //进一步解释下这个true，因为skip默认为0就是没有要跳过的数字，也就是说visited[0]要是true
        visited[0] = true;
        for (int i = m; i <= n; i++) {
            result += dfs(1, visited, i - 1) * 4;
            result += dfs(2, visited, i - 1) * 4;
            result += dfs(5, visited, i - 1);
        }
        return result;
    }
    public int dfs(int curr, boolean[] visited, int remain) {
        if (remain == 0) { //返回
            return 1;
        }
        int result = 0; //直接在DFS中定义这个结果
        visited[curr] = true;
        for (int next = 1; next <= 9; next++) {
            if (next == curr) continue; //和当前点一样就跳过
            if(isValid(visited[next], visited[skip[curr][next]])){
                result += dfs(next, visited, remain - 1);
            }
        }
        visited[curr] = false;
        return result;
    }

    //什么时候才能进行下一次迭代
    private boolean isValid(boolean isNextVisited, boolean isMustNotSkipVisited) {
        //条件1：下一个节点还没有被访问
        //条件2：该节点中有不能跳过的节点，并且没有跳过
        return (!isNextVisited && isMustNotSkipVisited);
    }
}
