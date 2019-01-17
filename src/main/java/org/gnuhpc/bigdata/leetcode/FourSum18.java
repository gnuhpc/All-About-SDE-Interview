package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum18 {

    public static void main(String[] args) {
        int[] nums = new int[]{-3,-1,0,2,4,5};
        int target = 0;

        fourSum(nums,target);

    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //象征性的说，如果没有4个数，那还玩个球啊
        if(nums.length < 4) return res;
        //双指针排序，都看腻了吧
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-3; i++){
            //去掉重复的数字，就是打头不能相同
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1; j < nums.length-2; j++){
                //再去掉一遍
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                //实力打脸，以后还是要left和right，low和high太low了。
                int low = j+1, high = nums.length-1;
                while(low < high){
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if(sum == target){
                        //这里新建一个list也行。
                        res.add(Arrays.asList(nums[i],nums[j], nums[low], nums[high]));
                        while(low+1 < high && nums[low+1] == nums[low]){
                            low++;
                        }
                        while(high-1 > low && nums[high-1] == nums[high]){
                            high--;
                        }
                        low++;
                        high--;
                    }
                    else if(sum < target){
                        low++;
                    }
                    else{
                        high--;
                    }
                }
            }
        }
        return res;
    }
}
