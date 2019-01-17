package org.gnuhpc.bigdata.concurrency.masterslave;

public class ParallelSum {

	private ParallelWorker[] workers;
	private int numOfThreads;
	
	public ParallelSum(int numOfThreads) {
		this.workers = new ParallelWorker[numOfThreads];
		this.numOfThreads = numOfThreads;
	}
	
	public int parallelSum(int[] nums) {
		
		int size = (int) Math.ceil(nums.length * 1.0 / numOfThreads);


		for (int i = 0; i < numOfThreads; i++) {
			workers[i] = new ParallelWorker(nums, i * size, (i + 1) * size);
			workers[i].start();
		}

		try {
			for (ParallelWorker worker : workers) {
				worker.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int total = 0;

		for (ParallelWorker worker : workers) {
			total += worker.getPartialSum();
		}

		return total;
	}
}
