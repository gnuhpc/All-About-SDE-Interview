package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class IncreasingTriplet334 {
    //Method:  尝试法
    public boolean increasingTriplet(int[] nums) {
        int smallIndex, i = 0;
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;

        while (i < nums.length) {
            if (nums[i] < small){ //比最小的还小
                small = nums[i];
                //smallIndex = i; 这个解法还可以返回递增三元组的最小那个值
            }  else if (nums[i] > small && nums[i] <= big) { //是中间的那一个
                big = nums[i];
            } else if (nums[i] > big){ //比最大的还大，构成单调递增三元组
                return true;
            }
            i ++;
        }

        return false;
    }

    @Test
    public void test(){
        System.out.println(increasingTriplet(new int[]{2,4,-2,-3}));
    }
}
