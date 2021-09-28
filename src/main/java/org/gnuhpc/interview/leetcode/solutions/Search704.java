package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.algorithm.search.BinarySearch;

/**
 * Copyright gnuhpc 2020/8/5
 */
public class Search704 {
    public int search(int[] nums, int target) {
        return BinarySearch.binarySearch(target, nums);
    }

    /*
    Golang Version:
    func search(nums []int, target int) int {
        for l, r := 0, len(nums)-1; l<=r; {
            switch mid := (l+r)/2; {
            case nums[mid] > target:
                r = mid - 1
            case nums[mid] < target:
                l = mid + 1
            default:
                return mid
            }
        }
        return -1
    }

     */
}
