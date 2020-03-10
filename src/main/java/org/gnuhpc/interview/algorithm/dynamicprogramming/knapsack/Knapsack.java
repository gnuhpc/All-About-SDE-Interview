package org.gnuhpc.interview.algorithm.dynamicprogramming.knapsack;

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
		this.knapsackTable = new int[numOfItems + 1][capacityOfKnapsack + 1];
	}

	//Method 1 : DP  自底向上
	public void solve() {

		// time complexity: O(N*W)
		for (int i = 1; i < numOfItems + 1; i++) {
			for (int w = 0; w <= capacityOfKnapsack; w++) {

				int notTaking = knapsackTable[i - 1][w]; // not taking item i
				int taking = 0;

				if (weights[i] <= w) {
					taking = profits[i] + knapsackTable[i - 1][w - weights[i]];
				}

				knapsackTable[i][w] = Math.max(notTaking, taking);
			}
		}

		totalProfit = knapsackTable[numOfItems][capacityOfKnapsack];

	}

	// create a map to store solutions of subproblems
	private Map<String, Integer> lookup = new HashMap<>();

	// Method 2: recursive + memorization 自顶向下
	public void solveRecursively() {
		this.totalProfit = solve(profits.length - 1, capacityOfKnapsack);
	}

	private int solve(int idx, int w) {

		// base case: no items left or capacity becomes 0
		if (idx < 0 || w == 0) {
			return 0;
		}

		// construct a unique map key from dynamic elements of the input
		String key = idx + "|" + w;

		// if sub-problem is seen for the first time, solve it and
		// store its result in a map
		if (!lookup.containsKey(key)) {
			// Case 1. notTaking current item n from knapSack and recurse for
			// remaining items (n-1)
			int notTaking = solve(idx - 1, w);

			// Case 2. taking current item n in knapSack (v[n]) & recurse
			// for remaining items (n-1) with decreased capacity (W - w[n])
			int taking = 0;

			if (weights[idx] <= w) taking = profits[idx] + solve(idx - 1, w - weights[idx]);


			// assign max value we get by including or excluding current item
			lookup.put(key, Integer.max(taking, notTaking));
		}

		// return solution to current sub-problem
		return lookup.get(key);
	}

	public void showRecursiveResult() {
		System.out.println("Another round: Total max benefit is " + getTotalProfit());
	}

	public void showResult() {

		System.out.println("Total benefit: " + totalProfit);

		for (int n = numOfItems, w = capacityOfKnapsack; n > 0; n--) {
			if (knapsackTable[n][w] != 0 && knapsackTable[n][w] != knapsackTable[n - 1][w]) {
				System.out.println("We take item: #" + n);
				w = w - weights[n];
			}
		}
	}

	// add by tina,memo search
	// 状态定义
	// F(n,C) 考虑将n个物品放入容量为C的背包，时期价值最大化
	// F(i,C) = max(F(i-1,C),v(i)+F(i-1,C-w(i)))
	// 2个限制条件，2个参数，用二维数组表示
	private int[][] memo;//用一个hashmap<String,Integer>也可以

	public int Knapsack3(int[] w, int[] v, int C) {
		int n = w.length;
		memo = new int[n][n];
		return maxProfit(w, v, n - 1, C);

	}

	// [0,...,index]范围内的物品，填充容积为c的背包的最大价值
	public int maxProfit(int[] w, int[] v, int index, int c) {
		if (index < 0 || c <= 0) return 0;
		if (memo[index][c] != 0) return memo[index][c];
		int res = maxProfit(w, v, index - 1, c);
		//需要注意的是，这里需要加一下边界判断
		//不宜直接比较两个路径，因为c<w[index]属于不合法情况，但是会返回0
		if (c >= w[index])
			res = Math.max(res, v[index] + maxProfit(w, v, index - 1, c - w[index]));
		memo[index][c] = res;
		return res;
	}

}
