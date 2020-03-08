package org.gnuhpc.bigdata.leetcode.solutions;

public class IncreasingTripletSubsequence334 {
    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min2) return true;
            if (nums[i] < min1) min1 = nums[i];
            if (min1 < nums[i] && nums[i] < min2) min2 = nums[i];
        }
        return false;
    }

}
