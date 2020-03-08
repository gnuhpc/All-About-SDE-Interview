package org.gnuhpc.bigdata.leetcode.solutions;

public class Search81 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int partition = findPartition(nums);
        if (partition != -1) {
            int partitionNum = nums[partition];
            if (target >= nums[0] && target <= nums[partition - 1]) {
                return binarySearch(nums, 0, partition - 1, target);
            }
            else {
                return binarySearch(nums, partition, nums.length - 1, target);
            }
        }
        else {
            return binarySearch(nums, 0, nums.length - 1, target);
        }
    }

    private int findPartition(int[] nums) {
        int idx = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                idx = i;
                break;
            }
        }

        return idx;
    }

    private boolean binarySearch(int[] nums, int l, int r, int target) {
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return true;
            }
            if (nums[m] < target) {
                l = m;
            }
            else {
                r = m;
            }
        }

        if (nums[l] == target) return true;
        if (nums[r] == target) return true;
        return false;
    }
}
