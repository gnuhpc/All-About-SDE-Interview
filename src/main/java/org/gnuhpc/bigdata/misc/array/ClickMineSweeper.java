package org.gnuhpc.bigdata.misc.array;

import java.util.ArrayDeque;

public class ClickMineSweeper {
    public static void main(String[] args) {
        // NOTE: The following input profits will be used for testing your solution.
        int[][] field1 = {{0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, -1, 1, 0}};

        printResult(click(field1, 3, 5, 2, 2));// should return:
        // [[0, 0, 0, 0, 0],
        //  [0, 1, 1, 1, 0],
        //  [0, 1, -1, 1, 0]]

        printResult(click(field1, 3, 5, 1, 4));// should return:
        // [[-2, -2, -2, -2, -2],
        //  [-2, 1, 1, 1, -2],
        //  [-2, 1, -1, 1, -2]]


        int[][] field2 = {{-1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, -1}};

        printResult(click(field2, 4, 4, 0, 1));// should return:
        // [[-1, 1, 0, 0],
        //  [1, 1, 0, 0],
        //  [0, 0, 1, 1],
        //  [0, 0, 1, -1]]

        printResult(click(field2, 4, 4, 1, 3)); // should return:
        // [[-1, 1, -2, -2],
        //  [1, 1, -2, -2],
        //  [-2, -2, 1, 1],
        //  [-2, -2, 1, -1]]
    }

    public static int[][] click(int[][] field, int numRows, int numCols, int givenI, int givenJ) {
        if (field[givenI][givenJ] == 0 ) {
            ArrayDeque<int[]> toCheck = new ArrayDeque<>();
            field[givenI][givenJ] = -2;
            int[] coordinates = {givenI, givenJ};
            toCheck.add(coordinates);
            while(!toCheck.isEmpty()) {
                int[] currentCoordinates = toCheck.remove();
                int currentI = currentCoordinates[0];
                int currentJ = currentCoordinates[1];
                for (int i = currentI - 1; i < currentI + 2; i++) {
                    for (int j = currentJ - 1; j < currentJ + 2; j++) {
                        if (0 <= i && i < numRows && 0 <= j && j < numCols
                                && field[i][j] == 0) {
                            field[i][j] = -2;
                            int[] nextCoordinates = {i, j};
                            toCheck.add(nextCoordinates);
                        }
                    }
                }
            }
        }
        return field;
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
}
