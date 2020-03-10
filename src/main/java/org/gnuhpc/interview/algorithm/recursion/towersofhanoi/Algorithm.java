package org.gnuhpc.interview.algorithm.recursion.towersofhanoi;

public class Algorithm {

    public void solveHanoiProblem(int n, char rodFrom, char middleRod, char rodTo) {

        if (n == 1) {
            System.out.println("Plate 1 from " + rodFrom + " to " + rodTo);
            return;
        }

        //先把n-1个盘子放到mid
        solveHanoiProblem(n - 1, rodFrom, rodTo, middleRod);
        //再把最下边那个盘子放到to
        System.out.println("Plate " + n + " from " + rodFrom + " to " + rodTo);
        //最后把mid的n-1个盘子放到to
        solveHanoiProblem(n - 1, middleRod, rodFrom, rodTo);
    }
}
