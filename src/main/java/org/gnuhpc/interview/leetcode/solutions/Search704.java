package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.algorithm.search.BinarySearch;

/**
 * Copyright gnuhpc 2020/8/5
 */
public class Search704 {
    public int search(int[] nums, int target) {
        return BinarySearch.binarySearch(target, nums);
    }
}
