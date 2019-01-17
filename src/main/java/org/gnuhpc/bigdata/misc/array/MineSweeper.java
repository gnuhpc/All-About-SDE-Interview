package org.gnuhpc.bigdata.misc.array;

public class MineSweeper {
    public static void main(String[] args) {
        int[][] bombs1 = {{0, 2}, {2, 0}};
        int[][] result1 = mineSweeper(bombs1, 3, 3); //should return:
        printResult(result1);
        // [[0, 1, -1],
        //  [1, 1, 1],
        //  [-1, 1, 0]]

        int[][] bombs2 = {{0, 0}, {0, 1}, {1, 2}};
        int[][] result2 = mineSweeper(bombs2, 3, 4); //should return:
        printResult(result2);
        // [[-1, -1, 2, 1],
        //  [2, 3, -1, 1],
        //  [0, 1, 1, 1]]

        int[][] bombs3 = {{1, 1}, {1, 2}, {2, 2}, {4, 3}};
        int[][] result3 = mineSweeper(bombs3, 5, 5); //should return:
        printResult(result3);
        // [[1, 2, 2, 1, 0],
        //  [1, -1, -1, 2, 0],
        //  [1, 3, -1, 2, 0],
        //  [0, 1, 2, 2, 1],
        //  [0, 0, 1, -1, 1]]
    }

    private static void printResult(int[][] result) {
        for (int[] row : result) {
            for (int item: row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static int[][] mineSweeper(int[][] bombs, int numRows, int numCols) {
        int[][] field = new int[numRows][numCols]; //默认都是0

        for (int[] bomb: bombs) {
            int rowIndex = bomb[0];
            int colIndex = bomb[1];
            field[rowIndex][colIndex] = -1;
            for(int i = rowIndex - 1; i <= rowIndex + 1; i++) {
                for (int j = colIndex - 1; j <= colIndex + 1; j++) {
                    if (0 <= i && i < numRows &&
                            0 <= j && j < numCols &&
                            field[i][j] != -1) {
                        field[i][j] += 1;
                    }
                }
            }
        }
        return field;
    }
}
