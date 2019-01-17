package org.gnuhpc.bigdata.concurrency.forkjoinframework;

import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(400);
		forkJoinPool.invoke(simpleRecursiveAction);

		SimpleRecursiveActionReturn simpleRecursiveActionReturn = new SimpleRecursiveActionReturn(400);
		System.out.println( forkJoinPool.invoke(simpleRecursiveAction) );
	}
}
