package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges228 {
    public static void main(String[] args) {
        int[] array = new int[]{0,1,2,4,5,7};
        summaryRanges(array);
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int beginIdx = 0;
        int endIdx = 0;

        for (int i = 0; i < nums.length ; i++) {
            if (i == nums.length-1){ // handle the last element
                if (beginIdx == i){
                    result.add("" + nums[beginIdx]);
                } else{
                     result.add(nums[beginIdx]
                                + "->"
                                +nums[endIdx]
                        );
                }
            } else{
                if (nums[i+1] == nums[i] + 1){
                    endIdx++;

                } else {
                    if (beginIdx!=endIdx){
                        result.add(nums[beginIdx]
                                +"->"
                                +nums[endIdx]
                        );
                    } else {
                        result.add(""+nums[beginIdx]);
                    }
                    beginIdx = endIdx+1;
                    endIdx = beginIdx;
                }
            }
        }

        return result;

    }

    /*
    Method 双指针
     */

    public List<String> summaryRanges2(int[] nums)
    {
        List<String> res = new ArrayList<>();

        if(nums == null || nums.length == 0)
            return res;

        int left = 0, right = 0;

        for(int i=1; i<nums.length; i++)
        {
            // if current number is not equal to previous number + 1, add the range into list
            if(nums[i] != nums[i-1] + 1)
            {
                // if just one number
                if(left == right)
                    res.add("" + nums[left]);
                else// if more than one number
                    res.add("" + nums[left] + "->" + nums[right]);

                // if find the gap, update the left and right
                left = i;
                right = i;
            }
            else if(nums[i] == nums[i-1] + 1) // if find the correct number, increase right
                right = i;

        }

        // add the last range
        if(left == right)
            res.add("" + nums[left]);
        else // if more than one number
            res.add("" + nums[left] + "->" + nums[right]);

        return res;
    }
}
