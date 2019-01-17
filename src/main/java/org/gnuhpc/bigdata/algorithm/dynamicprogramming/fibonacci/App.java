package org.gnuhpc.bigdata.algorithm.dynamicprogramming.fibonacci;

public class App {
	public static void main(String[] args) {
		FibonacciAlgorithm fa = new FibonacciAlgorithm(38);

		long starttime = System.nanoTime();
		System.out.println(fa.calc1());
		System.out.println("Cost: " + String.valueOf(System.nanoTime() - starttime));

		starttime = System.nanoTime();
		System.out.println(fa.calc2());
		System.out.println("Cost: " + String.valueOf(System.nanoTime() - starttime));

		starttime = System.nanoTime();
		System.out.println(fa.calc3());
		System.out.println("Cost: " + String.valueOf(System.nanoTime() - starttime));
	}
}
