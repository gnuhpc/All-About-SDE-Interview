package org.gnuhpc.interview.concurrency.forkjoinframework.findmax;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

    public static int THREASHOLD = 10;

    public static void main(String[] args) {

        long[] nums = initializeNums();
        THREASHOLD = nums.length / Runtime.getRuntime().availableProcessors();

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        MaximumFindTask findTask = new MaximumFindTask(nums, 0, nums.length);

        long start = System.currentTimeMillis();
        System.out.println("Max: " + forkJoinPool.invoke(findTask));
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static long[] initializeNums() {
        Random random = new Random();

        long[] nums = new long[3000000];

        for (int i = 0; i < 3000000; ++i)
            nums[i] = random.nextInt(100000000);

        return nums;
    }
}
