package org.gnuhpc.bigdata.concurrency.masterslave;

import org.gnuhpc.bigdata.leetcode.utils.Utils;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-01 22:21
 * Version: 0.1
 */
public class App {
    public static void main(String[] args) {
        ParallelSum parallelSum = new ParallelSum(16);
		int res = parallelSum.parallelSum(Utils.generateRandomArray(100,0,100));
		System.out.println(res);
    }
}
