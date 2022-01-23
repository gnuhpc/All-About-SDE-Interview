package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class IncreasingTriplet334 {
    //Method:  尝试法
    public boolean increasingTriplet(int[] nums) {

        int small = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < small) { //比最小的还小
                small = nums[i];
            }

            if (nums[i] > mid) { //比最大的还大，构成单调递增三元组
                return true;
            } else if (nums[i] > small && nums[i] < mid) { //是中间的那一个
                mid = nums[i];
            }
        }

        return false;
    }


    @Test
    public void test() {
        System.out.println(increasingTriplet(new int[]{2, 4, -2, -3}));
    }
}
