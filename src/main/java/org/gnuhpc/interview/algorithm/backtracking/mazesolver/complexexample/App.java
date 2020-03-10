package org.gnuhpc.interview.algorithm.backtracking.mazesolver.complexexample;

public class App {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader("C:\\Users\\Balazs_Holczer\\Desktop\\map.txt", 5, 5);
        fileReader.parseFile();
        MazeSolver mazeSolver = new MazeSolver(fileReader.getMap(), fileReader.getStartPositionRow(), fileReader.getStartPositionCol());
        mazeSolver.findWayOut();

    }
}
