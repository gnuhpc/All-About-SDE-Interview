package org.gnuhpc.interview.algorithm.backtracking.mazesolver;

public class App {

    public static void main(String[] args) {

        int mazeTable[][] = {{1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1}
        };

        Maze mazeProblem = new Maze(mazeTable);
        mazeProblem.solveMaze();

    }
}
