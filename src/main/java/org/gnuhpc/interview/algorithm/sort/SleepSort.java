package org.gnuhpc.interview.algorithm.sort;

import java.util.concurrent.CountDownLatch;

public class SleepSort {

    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 1, 5, 6};
        sleepSortAndPrint(nums);
    }

    public static void sleepSortAndPrint(int[] nums) {

        final CountDownLatch latch = new CountDownLatch(nums.length);

        for (final int num : nums) {
            new Thread(() -> {
                latch.countDown();
                try {
                    latch.await();
                    Thread.sleep(num * 100);
                    System.out.print(num + "  ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
