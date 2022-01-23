package org.gnuhpc.interview.leetcode.solutions;

public class DominantIndex747 {
    public int dominantIndex(int[] nums) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        int maxId = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > first) {
                second = first;
                first = nums[i];
                maxId = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        return first >= 2 * second ? maxId : -1;
    }

}
