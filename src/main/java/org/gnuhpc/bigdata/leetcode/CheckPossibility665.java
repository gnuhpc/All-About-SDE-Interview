package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class CheckPossibility665 {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] > nums[i + 1]) {
                if (count > 0)  //分别出现多次直接返回false
                    return false;
                if (i - 1 >= 0 && i + 2 < nums.length && // 出现在不是开头，且出现w型
                        (nums[i] > nums[i + 2] && nums[i - 1] > nums[i + 1]))
                    return false;
                count++;
            }
        return true;

    }

    @Test
    public void test(){
        int[] arr = {4,2,3};
        System.out.println(checkPossibility(arr));

        int[] arr2 = {3,4,2,3};
        System.out.println(checkPossibility(arr2));

        int[] arr3 = {4,2,1};
        System.out.println(checkPossibility(arr3));
    }
}
