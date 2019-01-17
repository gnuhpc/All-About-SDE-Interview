package org.gnuhpc.bigdata.algorithm.dynamicprogramming.subsetsum;

public class App {


	public static void main(String[] args) {
		
		int[] numbers = {5, 2, 3, 1};
		int sum = 9;
		
		SubsetSumProblem subsetSumProblem = new SubsetSumProblem(numbers, sum);
		subsetSumProblem.solveProblem();
		subsetSumProblem.hasSolution();
		subsetSumProblem.showSums();
		
	}
}
