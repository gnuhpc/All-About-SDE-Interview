package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

//明确一个不争的事实，数组是一个特殊的数组，里面的数都在[1,length-1]范围内
public class FindDuplicate287 {
    public int findDuplicate(int[] nums) {
        if (nums.length <=1)
            return 0;
        if(nums.length == 2){
            return nums[0];
        }
        int small = 1, big = nums.length - 1, guess;
        while (small < big) {
            // 猜测一个数字，比如中间的一个，其实猜哪个数字都无所谓！！，你改为10也行，3也行，只是效率不行
            // 猜2可以将一半的数字剔除，才是关键
            // 这也解释了为什么二分法有时候不见得非要有序。一次可以排除一半的解法统称为二分法。
            guess = small + (big - small) / 2;
            //然后计算比这个猜的数字小于等于的个数
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= guess)
                    count++;
            }
            //如果这个个数大于猜测这个数字的本身，那么猜测的范围不会超过guess
            if (count > guess)
                big = guess;
            else // 反之不会低于guess
                small = guess + 1;
        }
        return small;
    }


    @Test
    public void test(){
        System.out.println(findDuplicate(new int[]{3,1,3,4,2}));
        System.out.println(findDuplicate(new int[]{1,1,2}));
    }
}
