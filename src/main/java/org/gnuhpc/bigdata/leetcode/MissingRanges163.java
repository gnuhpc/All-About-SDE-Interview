package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges163 {
    // 首位区间的处理，需要与面试官进一步沟通
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> lst = new ArrayList<>();
        if(nums == null || nums.length == 0) return lst;
        int begin = nums[0];
        if(lower +1 == begin) lst.add(lower+"");
        else if (lower+1<begin) lst.add(lower+"->"+ (begin-1));
        for(int i = 1; i<nums.length;i++){
            if(nums[i-1] +1 != nums[i]) {
                if(nums[i-1] + 2 == nums[i]) lst.add(nums[i-1]+1+"");
                else lst.add(nums[i-1]+1+"->" + (nums[i]-1));
            }
        }
        int last = nums[nums.length-1];
        if(last+1 == upper) lst.add(upper+"");
        else lst.add(last+1+"->" + (upper)); //包括upper
        return lst;
    }

    @Test
    public void test(){
        int[] nums = {0, 1, 3, 50, 75};
        List<String> res = findMissingRanges(nums,0,99);
        System.out.println(res.toString());
    }

}

