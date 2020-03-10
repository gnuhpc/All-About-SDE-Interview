package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class GameOfLife289 {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                changeBorder(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }

    }

    public void changeBorder(int[][] board, int x, int y) {
        int row = board.length;
        int col = board[0].length;

        int total = 0; // 这是记录该细胞周围活细胞的数量

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (i >= 0 && i < row && j >= 0 && j < col) {
                    if (board[i][j] > 0) {
                        total++;
                    }
                }
            }
        }
        //把即将死亡的 活细胞 置为 2
        if (board[x][y] > 0) {
            if (total < 2 || total > 3) {
                board[x][y] = 2;
            }
        }
        //把即将复活的 死细胞 置为 -1
        if (board[x][y] <= 0 && total == 3) {
            board[x][y] = -1;
        }

    }


    int[][] a;

    @Test
    public void test() {
        a = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        gameOfLife(a);
        Utils.print2DArray(a);

    }

    //add by tina

    /**
     * 这道题是有名的康威生命游戏, 而我又是第一次听说这个东东，这是一种细胞自动机，每一个位置有两种状态，1为活细胞，0为死细胞，对于每个位置都满足如下的条件：
     * <p>
     * 1. 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡
     * <p>
     * 2. 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活
     * <p>
     * 3. 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡
     * <p>
     * 4. 如果死细胞周围正好有三个活细胞，则该位置死细胞复活
     * <p>
     * 由于题目中要求我们用置换方法in-place来解题，所以我们就不能新建一个相同大小的数组，那么我们只能更新原有数组，但是题目中要求所有的位置必须被同时更新，但是在循环程序中我们还是一个位置一个位置更新的，那么当一个位置更新了，这个位置成为其他位置的neighbor时，我们怎么知道其未更新的状态呢，我们可以使用状态机转换：
     * <p>
     * 状态0： 死细胞转为死细胞
     * <p>
     * 状态1： 活细胞转为活细胞
     * <p>
     * 状态2： 活细胞转为死细胞
     * <p>
     * 状态3： 死细胞转为活细胞
     * <p>
     * 最后我们对所有状态对2取余，那么状态0和2就变成死细胞，状态1和3就是活细胞，达成目的。我们先对原数组进行逐个扫描，对于每一个位置，扫描其周围八个位置，如果遇到状态1或2，就计数器累加1，扫完8个邻居，如果少于两个活细胞或者大于三个活细胞，而且当前位置是活细胞的话，标记状态2，如果正好有三个活细胞且当前是死细胞的话，标记状态3。完成一遍扫描后再对数据扫描一遍，对2取余变成我们想要的结果。
     *
     * @param board
     */
    public void gameOfLife2(int[][] board) {
        int m = board.length;
        int n = m > 0 ? board[0].length : 0;
        // 定义两个数组描述8个邻居的偏移量，一个二维数组也可以。
        int dx[] = {-1, -1, -1, 0, 1, 1, 1, 0};
        int dy[] = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int cnt = 0;
                // 借助偏移量数组来进行邻居遍历
                for (int k = 0; k < 8; ++k) {
                    int x = i + dx[k], y = j + dy[k];
                    // 下标有效性判断，且仅对原来是活的状态进行统计
                    if (x >= 0 && x < m && y >= 0 && y < n
                            && (board[x][y] == 1 || board[x][y] == 2)) {
                        ++cnt;
                    }
                }
                // 仅需考虑状态需要转换的，如果不需要状态转换的不需要动
                if (board[i][j] == 1 && (cnt < 2 || cnt > 3)) board[i][j] = 2;
                else if (board[i][j] == 0 && cnt == 3) board[i][j] = 3;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] %= 2;
            }
        }
    }

}
