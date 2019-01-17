package org.gnuhpc.bigdata.concurrency.forkjoinframework;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveActionReturn extends RecursiveTask<Integer> {

	private int simulatedWork;
	
	public SimpleRecursiveActionReturn(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}
	
	@Override
	protected Integer compute() {
		
		if( simulatedWork > 100 ) {
			
			System.out.println("Parallel execution and split the tasks..." + simulatedWork);
			
			SimpleRecursiveActionReturn simpleRecursiveAction1 = new SimpleRecursiveActionReturn(simulatedWork/2);
			SimpleRecursiveActionReturn simpleRecursiveAction2 = new SimpleRecursiveActionReturn(simulatedWork/2);
			
			simpleRecursiveAction1.fork();
			simpleRecursiveAction2.fork();
			
			int solution = 0;
			solution = solution + simpleRecursiveAction1.join();
			solution = solution + simpleRecursiveAction2.join();
			
			return solution;
			
		} else {
			System.out.println("No need for parallel execution, sequential is OK for this task..." );
			return 2 * simulatedWork;
		}
	}
}
