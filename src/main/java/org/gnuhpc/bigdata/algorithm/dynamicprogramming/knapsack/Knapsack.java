package org.gnuhpc.bigdata.algorithm.dynamicprogramming.knapsack;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Knapsack {

	private int numOfItems;
	private int capacityOfKnapsack;
	/*
	S[i][w] = the maximum cost of items that fit inside (<=) a knapsack of size (weight) w,
	choosing from the first i items !!!

	 */
	private int[][] knapsackTable;
	private int totalProfit;
	private int[] weights;
	private int[] profits;

	public Knapsack(int numOfItems, int capacityOfKnapsack, int[] weights, int[] profits) {
		this.numOfItems = numOfItems;
		this.capacityOfKnapsack = capacityOfKnapsack;
		this.weights = weights;
		this.profits = profits;
		this.knapsackTable = new int[numOfItems+1][capacityOfKnapsack+1];
	}

	//Method 1 : DP  自底向上
	public void solve() {

		// time complexity: O(N*W)
		for(int i=1;i<numOfItems+1;i++) {
			for(int w=0;w<=capacityOfKnapsack;w++) {

				int notTaking = knapsackTable[i-1][w]; // not taking item i
				int taking= 0;

				if( weights[i] <= w ) {
					taking = profits[i] + knapsackTable[i-1][w-weights[i]];
				}

				knapsackTable[i][w] = Math.max(notTaking, taking);
			}
		}

		totalProfit = knapsackTable[numOfItems][capacityOfKnapsack];

	}

	// create a map to store solutions of subproblems
	private Map<String, Integer> lookup = new HashMap<>();

	// Method 2: recursive + memorization 自顶向下
	public void solveRecursively(){
        this.totalProfit = solve(profits.length-1,capacityOfKnapsack);
	}

	private int solve(int idx, int w){

		// base case: no items left or capacity becomes 0
		if (idx < 0 || w == 0) {
			return 0;
		}

		// construct a unique map key from dynamic elements of the input
		String key = idx + "|" + w;

		// if sub-problem is seen for the first time, solve it and
		// store its result in a map
		if (!lookup.containsKey(key))
		{
			// Case 1. notTaking current item n from knapSack and recurse for
			// remaining items (n-1)
			int notTaking = solve( idx - 1, w);

			// Case 2. taking current item n in knapSack (v[n]) & recurse
			// for remaining items (n-1) with decreased capacity (W - w[n])
			int taking = 0;

			if (weights[idx]<=w) taking = profits[idx] + solve(idx - 1, w - weights[idx]);


            // assign max value we get by including or excluding current item
			lookup.put(key, Integer.max(taking, notTaking));
		}

		// return solution to current sub-problem
		return lookup.get(key);
    }

    public void showRecursiveResult(){
        System.out.println("Another round: Total max benefit is " + getTotalProfit());
    }
	
	public void showResult() {
		
		System.out.println("Total benefit: " + totalProfit);
		
		for(int n=numOfItems, w = capacityOfKnapsack;n>0;n--) {
			if( knapsackTable[n][w] != 0 && knapsackTable[n][w] != knapsackTable[n-1][w] ) {
				System.out.println("We take item: #"+n);
				w = w - weights[n];
			}
		}
	}
}
