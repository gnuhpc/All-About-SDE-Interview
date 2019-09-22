package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 2019/9/22
 */
public class GetModifiedArray370 {
    /*

    segment [i,j] is made of two parts [0,i-1] and [0, j]
so [i,j] increase 2 is same as [0,j] increase 2 and [0,i-1] increase -2. so you only need to update value at nums[j] with inc and nums[i-1] -inc. initially nums[i] is defined as all elements [0,i]  increases inc
then think from length-1 to 0 backward. The last spot nums[length-1] does not need any modification.
nums[length-2] value should be updated as nums[length-2] + nums[length-1] as the latter covers the front. but front does not influence what is after it. so every spot should be updated as + the accumulate sum from the end.
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        for (int[] update : updates) {
            nums[update[1]] += update[2];
            if (update[0] > 0) {
                nums[update[0] - 1] -= update[2];
            }
        }

        int sum = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            int tmp = sum + nums[i];
            nums[i] += sum;
            sum = tmp;
        }
        return nums;
    }
}
