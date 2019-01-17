package org.gnuhpc.bigdata.concurrency.basic;

import java.util.concurrent.*;

/**
	 * 
	 *  1.) ExecutorService es = Executors.newCachedThreadPool();
	 *  	- going to return an executorService that can dynamically
	 *  		reuse threads
	 *		- before starting a job -> it going to check whether there are any threads that
	 *			finished the job...reuse them
	 *		- if there are no waiting threads -> it is going to create another one 
	 *		- good for the processor ... effective solution !!!
	 *
	 *	2.) ExecutorService es = Executors.newFixedThreadPool(N);
	 *		- maximize the number of threads
	 *		- if we want to start a job -> if all the threads are busy, we have to wait for one
	 *			to terminate
	 *
	 *	3.) ExecutorService es = Executors.newSingleThreadExecutor();
	 *		It uses a single thread for the job
	 *
	 *		execute() -> runnable + callable
	 *		submit() -> runnable
	
	 */

class EWorker implements Runnable{
    private CountDownLatch latch;

	public EWorker(CountDownLatch latch) {
	    this.latch = latch;
	}

	@Override
		public void run() {
			for(int i=0;i<10;i++){
				System.out.println(i);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			latch.countDown();
		}
}

public class ExecutorServiceDemo {
	public static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
			}
		} catch (InterruptedException ex) {
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		boolean daemonThread = true;
		CountDownLatch latch = new CountDownLatch(5);
		//设置创建thread的工厂方法类
		ThreadFactory threadFactory =
				runnable -> {
					Thread thr = new Thread(runnable);
					if (daemonThread)
						thr.setDaemon(true);
					return thr;
				};
		ExecutorService executorService = Executors.newFixedThreadPool(5, threadFactory);
		
		for(int i=0;i<5;i++){
			executorService.execute(new EWorker(latch));
		}

		latch.await();
		executorService.shutdown();

		//awaitTerminationAfterShutdown(executorService);
	}
}
