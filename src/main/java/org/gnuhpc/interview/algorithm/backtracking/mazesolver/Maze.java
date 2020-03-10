package org.gnuhpc.interview.algorithm.backtracking.mazesolver;

public class Maze {

    private int[][] mazeTable;
    private int[][] solutionTable;
    private int mazeSize;

    public Maze(int[][] mazeTable) {
        this.mazeTable = mazeTable;
        this.mazeSize = mazeTable.length;
        this.solutionTable = new int[this.mazeSize][this.mazeSize];
    }

    public void solveMaze() {

        if (solve(0, 0)) {
            showResult();
        } else {
            System.out.print("No feasible solution has found...");
        }
    }

    public boolean solve(int x, int y) {

        if (isFinished(x, y)) {
            return true;
        }

        if (isValid(x, y)) {

            solutionTable[x][y] = 1; // it is valid so it is part of the solution

            //注意是两个方向

            if (solve(x + 1, y)) {
                return true;  // go forward in x direction
            }

            if (solve(x, y + 1)) // If moving in x direction is not feasible: go down in y direction
                return true;

            solutionTable[x][y] = 0; // no feasible solution: backtrack
        }

        return false;
    }

    public boolean isFinished(int x, int y) {

        if (x == this.mazeSize - 1 && y == this.mazeSize - 1) {
            solutionTable[x][y] = 1;
            return true;
        }

        return false;
    }

    public boolean isValid(int x, int y) {

        if (x < 0 || x >= this.mazeSize) return false;
        if (y < 0 || y >= this.mazeSize) return false;
        if (this.mazeTable[x][y] != 1) return false;

        return true;
    }

    public void showResult() {

        for (int i = 0; i < this.mazeSize; ++i) {
            for (int j = 0; j < this.mazeSize; ++j) {
                if (this.solutionTable[i][j] == 1) {
                    System.out.print(" S ");
                } else {
                    System.out.print(" - ");
                }
            }

            System.out.println();
        }
    }
}
