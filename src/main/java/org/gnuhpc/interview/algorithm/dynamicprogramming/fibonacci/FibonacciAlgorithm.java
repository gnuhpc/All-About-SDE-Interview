package org.gnuhpc.interview.algorithm.dynamicprogramming.fibonacci;

import java.util.Arrays;

public class FibonacciAlgorithm {
	private int n;

	public FibonacciAlgorithm(int n) {
		this.memorizeTable = new int[n + 1];
		this.n = n;
		Arrays.fill(memorizeTable, -1);
	}

	public int calc1() {
		return naiveFibonacci(n);
	}

	public int calc2() {
		return fibonacciMemoize(n);
	}

	public int calc3() {
		return fibonacciTabulation(n);
	}


	// Method1 : Recursive  第一思路
	public int naiveFibonacci(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;

		return (naiveFibonacci(n - 1) + naiveFibonacci(n - 2));
	}

	//Method2 : Memoization (Top Down) 由Method1修改而来
	private int[] memorizeTable; // O(1)

	public int fibonacciMemoize(int n) {
		if (n == 0) {
			memorizeTable[0] = 0;
			return 0;
		}
		if (n == 1) {
			memorizeTable[1] = 1;
			return 1;
		}

		if (this.memorizeTable[n] != -1) return this.memorizeTable[n];


		//记录记忆化搜索表
		this.memorizeTable[n - 1] = fibonacciMemoize(n - 1);
		this.memorizeTable[n - 2] = fibonacciMemoize(n - 2);

		//然后考虑最复杂情况
		int calculatedNumber = this.memorizeTable[n - 1] + this.memorizeTable[n - 2];
		//记录记忆化搜索表
		this.memorizeTable[n] = calculatedNumber;

		return calculatedNumber;

	}

	// Method 3: Tabulation (Bottom Up) 由Method2修改而来
	private int fibonacciTabulation(int n) {
		memorizeTable[0] = 0;
		memorizeTable[1] = 1;
		//由这个循环替代递归
		for (int i = 2; i <= n; i++)
			memorizeTable[i] = memorizeTable[i - 1] + memorizeTable[i - 2];
		return memorizeTable[n];
	}
}
