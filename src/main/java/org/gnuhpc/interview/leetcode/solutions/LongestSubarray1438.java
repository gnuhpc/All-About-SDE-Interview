package org.gnuhpc.interview.leetcode.solutions;

import java.util.TreeMap;

/**
 * Copyright gnuhpc 2021/2/21
 */
public class LongestSubarray1438 {
    public int longestSubarray(int[] nums, int limit) {

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int left = 0, right = 0, size = 0, maxSize = 0, len = nums.length;
        while (right < len) {
            int item = nums[right];
            treeMap.put(item, treeMap.getOrDefault(item, 0) + 1);
            right++;
            while (left <= right && treeMap.lastKey() - treeMap.firstKey() > limit) {
                if (treeMap.get(nums[left]) == 1) {
                    treeMap.remove(nums[left]);
                } else {
                    treeMap.put(nums[left], treeMap.get(nums[left]) - 1);
                }
                left++;
            }
            maxSize = Math.max(maxSize, right - left);
        }
        return maxSize;
    }

    public int longestSubarray2(int[] nums, int limit) {
        int len = nums.length;
        int res = 0;
        int left = 0;
        int right = 0;
        int min = nums[left];
        int max = nums[left];

        for (right = 0; right < len; right++) {
            min = Math.min(min, nums[right]);
            max = Math.max(max, nums[right]);
            if (max - min <= limit) {
                res = Math.max(res, right - left + 1);
                continue;
            } else {
//                移动左边界
                while (true) {
                    left++;
                    if (nums[left] == nums[left - 1]) {
                        continue;
                    }
                    max = getMax(nums, left, right);
                    min = getMin(nums, left, right);
                    if (max - min <= limit) {
                        res = Math.max(res, right - left + 1);
                        break;
                    }
                }
            }
        }

        return res;
    }

    public int getMax(int[] nums, int start, int end) {
        int max = nums[start];
        for (int i = start + 1; i <= end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public int getMin(int[] nums, int start, int end) {
        int min = nums[start];
        for (int i = start + 1; i <= end; i++) {
            min = Math.min(min, nums[i]);
        }
        return min;
    }

}
